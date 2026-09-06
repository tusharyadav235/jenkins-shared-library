def call() {

    echo "Running OWASP dependency check"

    dir('cart-service') {
        sh '''
            mvn org.owasp:dependency-check-maven:check
        '''
    }

    echo "OWASP dependency check completed"
}
