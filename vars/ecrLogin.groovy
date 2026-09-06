def call(awsRegion, ecrRegistry) {

    echo "Authenticating Docker with ECR"

    sh """
        aws ecr get-login-password \
        --region ${awsRegion} | \
        docker login \
        --username AWS \
        --password-stdin ${ecrRegistry}
    """

    echo "Docker authentication with ECR successful"
}
