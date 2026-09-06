def call(serviceDir) {

    echo "Building ${serviceDir}"

    dir(serviceDir) {
        sh '''
            npm ci
            npm run build
        '''
    }

    echo "Frontend build completed successfully"
}
