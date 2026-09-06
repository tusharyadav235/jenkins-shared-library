def call(serviceDir){
   echo "Building the application"

    dir(serviceDir) {
        sh '''
            mvn clean compile
        '''
    }

    echo "Build completed successfully"
}
