def call(serviceName, gitopsRepo, gitopsBranch, imageName, imageTag, ecrRegistry) {

    echo "Promoting ${serviceName} to Dev"

    dir('k8s') {

        deleteDir()

        withCredentials([
            string(
                credentialsId: 'gitops-token',
                variable: 'GITOPS_TOKEN'
            )
        ]) {

            sh """
                git clone \
                --branch ${gitopsBranch} \
                https://x-access-token:${GITOPS_TOKEN}@github.com/${gitopsRepo}.git .
            """

            dir('overlays/dev') {

                sh """
                    kustomize edit set image \
                    ${serviceName}=${ecrRegistry}/${imageName}:${imageTag}

                    kustomize build . > /tmp/dev-manifest.yaml

                    test -s /tmp/dev-manifest.yaml
                """
            }

            sh """
                git config user.name "jenkins"
                git config user.email "jenkins@yourcompany.com"

                git add overlays/dev/kustomization.yaml

                if git diff --cached --quiet; then
                    echo "No GitOps changes required"
                    exit 0
                fi

                git commit \
                -m "Promote ${serviceName} ${imageTag} to dev"

                git push origin ${gitopsBranch}
            """
        }
    }
}
