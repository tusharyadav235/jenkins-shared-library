def call(url,branch){
   echo "Code checkout started"

  git url: url,
  branch: branch

  script {
      env.IMAGE_TAG = sh(
      script: 'git rev-parse --short HEAD',
      returnStdout: true
        ).trim()
          }

      echo "Image tag: ${IMAGE_TAG}"
      echo "Code checkout completed"
}
