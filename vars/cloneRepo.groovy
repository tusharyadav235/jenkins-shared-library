def call(url, branch) {
    echo "Code checkout started"

    git url: url, branch: branch

   
    env.IMAGE_TAG = sh(
        script: 'git rev-parse --short HEAD',
        returnStdout: true
    ).trim()

    
    echo "Image tag: ${env.IMAGE_TAG}" 
    echo "Code checkout completed"
}
