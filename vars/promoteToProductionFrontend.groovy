def call(serviceName, gitopsRepo, imageName, imageTag, ecrRegistry) {

    echo "Creating PR to promote ${serviceName} to Production"

    // Generate branch name automatically
    def branchName = "promote/${serviceName}-prod-${imageTag}"

    dir('k8s') {

        deleteDir()

        withCredentials([
            string(
                credentialsId: 'gitops-token',
                variable: 'GITOPS_TOKEN'
            )
        ]) {

            // Clone GitOps repository
            sh """
                git clone \
                https://x-access-token:${GITOPS_TOKEN}@github.com/${gitopsRepo}.git .
            """

            // Create production promotion branch
            sh """
                git checkout -b ${branchName}
            """

            // Update production image
            dir('overlays/prod') {

                sh """
                    kustomize edit set image \
                    ${serviceName}=${ecrRegistry}/${imageName}:${imageTag}

                    kustomize build . > /tmp/prod-manifest.yaml

                    test -s /tmp/prod-manifest.yaml
                """
            }

            // Commit and push
            sh """
                git config user.name "jenkins"
                git config user.email "jenkins@yourcompany.com"

                git add overlays/prod/kustomization.yaml

                if git diff --cached --quiet; then
                    echo "No production GitOps changes required"
                    exit 0
                fi

                git commit \
                -m "Promote ${serviceName} ${imageTag} to production"

                git push origin ${branchName}
            """

            // Create GitHub Pull Request
            sh """
                curl -X POST \
                  -H "Authorization: Bearer ${GITOPS_TOKEN}" \
                  -H "Accept: application/vnd.github+json" \
                  https://api.github.com/repos/${gitopsRepo}/pulls \
                  -d '{
                    "title": "Promote ${serviceName} to Production (${imageTag})",
                    "body": "Automated PR to promote ${serviceName} image ${imageTag} to production. Please review and merge.",
                    "head": "${branchName}",
                    "base": "main"
                  }'
            """
        }
    }
}
