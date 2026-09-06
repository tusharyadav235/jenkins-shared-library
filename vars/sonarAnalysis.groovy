def call() {

    echo "Running SonarQube analysis"

    withCredentials([
        string(
            credentialsId: 'sonar-token',
            variable: 'SONAR_TOKEN'
        )
    ]) {

        withSonarQubeEnv('sonar-server-name') {

            dir('cart-service') {

                sh '''
                    mvn sonar:sonar \
                    -Dsonar.token="$SONAR_TOKEN"
                '''
            }
        }
    }

    echo "SonarQube analysis completed"
}
