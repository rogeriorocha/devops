

# install Helm Cli (https://docs.helm.sh/using_helm/#from-script)

curl -LO https://git.io/get_helm.sh
chmod 700 get_helm.sh
./get_helm.sh




# apply path
kubectl patch deployment tiller -n kube-system --patch "$(cat 02-tiller-path.yaml)"