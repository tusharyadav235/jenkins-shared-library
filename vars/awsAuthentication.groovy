def call() {

    echo "Checking AWS identity"

    sh '''
        aws sts get-caller-identity
    '''

    echo "AWS authentication successful"
}
