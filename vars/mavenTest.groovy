def call(serviceDir) {

    echo "Running unit tests"

    dir(serviceDir) {
        sh 'mvn test'
    }

    echo "Unit tests completed successfully"
}
