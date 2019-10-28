


# install Helm Cli (https://docs.helm.sh/using_helm/#from-script)
curl -LO https://git.io/get_helm.sh
chmod 700 get_helm.sh
./get_helm.sh



# install tiller
#OPC 1:
kubectl apply -f 01-tiller-account.yaml
helm init --service-account tiller

#helm init 
# apply path
#kubectl patch deployment tiller -n kube-system --patch "$(cat 02-tiller-path.yaml)"




OU
#OPC 2:
kubectl -n kube-system create serviceaccount tiller

kubectl create clusterrolebinding tiller \
  --clusterrole=cluster-admin \
  --serviceaccount=kube-system:tiller

helm init --service-account tiller



# OBS: allow master to run pods:
kubectl taint nodes --all node-role.kubernetes.io/master-


=======================================================
#update 
helm repo update

#
helm repo list

#
helm search nginx


#
helm install stable/tomcat

#
helm install stable/tomcat