def call(serviceDir){
   echo "Building the application"

    dir('cart-service') {
        sh '''
            mvn clean compile
        '''
    }

    echo "Build completed successfully"
}
