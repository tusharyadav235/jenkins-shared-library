def call(serviceDir) {
    echo "Running OWASP Dependency Check"

    withCredentials([string(credentialsId: 'nvd-api-key', variable: 'NVD_API_KEY')]) {
        dir(serviceDir) {
            sh '''
                mvn org.owasp:dependency-check-maven:check \
                -DnvdApiKey="$NVD_API_KEY"
            '''
        }
    }
}

