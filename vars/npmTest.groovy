def call(serviceDir) {

    echo "Running unit tests"

    dir(serviceDir) {
        sh '''
            npm test
        '''
    }

    echo "Unit tests completed successfully"
}
