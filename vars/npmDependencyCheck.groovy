def call(serviceDir) {

    echo "Running NPM audit"

    dir(serviceDir) {
        sh '''
            npm audit --audit-level=high
        '''
    }

    echo "NPM audit completed successfully"
}
