def call(serviceDir) {

    echo "Running SonarQube analysis"

    withCredentials([
        string(
            credentialsId: 'sonar-token',
            variable: 'SONAR_TOKEN'
        )
    ]) {

        withSonarQubeEnv('sonar-server-name') {

            dir(serviceDir) {

                sh '''
                    mvn sonar:sonar \
                    -Dsonar.token="$SONAR_TOKEN"
                '''
            }
        }
    }

    echo "SonarQube analysis completed"
}
