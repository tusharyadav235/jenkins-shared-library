def call(
  echo "Checking required tools"

  sh '''
      java -version
      mvn -version
      docker --version
      aws --version
      trivy --version
      kustomize version
                '''

  echo "All required tools are available"
)
