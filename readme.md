## minikube

* commands
```sh
#start with 2 CPUs and 8Gb RAM
minikube start --cpus 2 --memory 8192

# go bash
minikube ssh

# show ip
minikube ssh

# work with daemon of docker
eval $(minikube docker-env)


# show dashboard kubernets
minikube dashboard

#show addons
minikube addons list
```



apiVersion: batch/v1beta1
kind: CronJob
metadata:
  name: hello
spec:
  schedule: "*/1 * * * *"
  concurrencyPolicy: Replace
  jobTemplate:
    spec:
      template:
        spec:
          containers:
          - name: hello
            image: busybox
            args:
            - /bin/sh
            - -c
            - date; echo "Hello, World!"
          restartPolicy: OnFailure