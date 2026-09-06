def call (serviceDir){

  echo "Packaging application"

    dir(serviceDir) {
        sh '''
            mvn package -DskipTests
        '''
    }

    echo "Packaging completed successfully"
}
