def call(serviceDir) {

    echo "Running OWASP dependency check"

    dir(serviceDir) {
        sh '''
            mvn org.owasp:dependency-check-maven:check
        '''
    }

    echo "OWASP dependency check completed"
}
