def call(imageName, imageTag, ecrRegistry) {

    echo "Tagging Docker image for ECR"

    sh """
        docker tag \
        ${imageName}:${imageTag} \
        ${ecrRegistry}/${imageName}:${imageTag}
    """

    echo "Docker image tagged successfully"
}
