# Fruits App Demo

A simple fruits API demo with Gloo Edge

# Tools and Frameworks

- [tektoncd](https://tekton.dev)

# Setup 

Install tektoncd,

```bash
kubectl apply \
   --context="$CLUSTER1" \
   --filename https://storage.googleapis.com/tekton-releases/pipeline/latest/release.yaml
```

```bash
tkn hub install task maven \
  --version=0.2 \
  --context="$CLUSTER1"
  
tkn hub install task git-clone \
  --version=0.5 \
  --context="$CLUSTER1"

tkn hub install task kaniko \
  --version=0.5 \
  --context="$CLUSTER1"
```

Deploy nexus to use for building java applications,

```bash
kubectl apply \
  --context $CLUSTER1 \
  --filename https://raw.githubusercontent.com/redhat-scholars/tekton-tutorial/master/install/utils/nexus.yaml
```

Create Tekton pipelines,

```bash
kubectl apply --context $CLUSTER1 --kustomize config/pipelines
```