def call() {

    echo "Waiting for SonarQube quality gate"

    timeout(time: 5, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }

    echo "SonarQube quality gate passed"
}
