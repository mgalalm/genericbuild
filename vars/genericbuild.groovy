def call(Map config=[:]) {
    node {
        stage('SCM') {
            echo 'Gathering code from  version control'
            git branch: '${branch}', url: 'https://github.com/mgalalm/devops.git'
        }
        stage('Build') {
            try {
                echo 'Building ...'
                echo "Build for " + config.project
                releasenotes()
            } catch (ex) {
                echo 'Something went wrong'
                echo ex.toString();
                currentBuild.result = 'FAILURE'
            }
            finally {
                // cleanup
            }
        }
        stage('Test') {
            echo 'Testing ...'
        }
        stage('Deploy') {
            echo 'Deploying ......'
        }

    }
}
