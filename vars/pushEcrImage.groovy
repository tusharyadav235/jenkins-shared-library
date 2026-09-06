def call(ecrRegistry, imageName, imageTag) {

    echo "Pushing Docker image to ECR"

    sh """
        docker push \
        ${ecrRegistry}/${imageName}:${imageTag}
    """

    echo "Docker image pushed successfully to ECR"
}
