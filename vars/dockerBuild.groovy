def call(serviceDir , imageName, imageTag){
echo "Building Docker image"

    dir(serviceDir) {
        sh """
            docker build \
            -t ${imageName}:${imageTag} \
            .
        """
    }

    echo "Docker image built successfully"
}
