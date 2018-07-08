# Google Cloud Datastore playground

Study of how Google Cloud Storage can be used from Scala, programmatically.

- writing
- watching for changes
- extracting the history of a key
- access rights; can we restrict access by bucket?

The focus is on features needed for a "slow data" adapter. E.g. writing multiple values atomically in a batch, over writing just a single value.


## Requirements

- `sbt`

### Setting up your Google Cloud Datastore

Note: We're expecting some knowledge on handling the [Google Cloud Console](https://console.cloud.google.com/).

1. Create a project, and a service account for it.

  Store the key for the service account under `secrets/`, and export it as (a sample):
  
  `export GOOGLE_APPLICATION_CREDENTIALS=secrets/datastore-playground-040718-581a7301690a.json 
  `

2. Enable Google Cloud Datastore for the project.

  Create in Datastore > Entities (actual values of data don't matter):
  
  ![](.images/entity-create.png)
  
  
## Kick the tires  

### MainRead

```
$ sbt "runMain main.MainRead"
...
Fetched 'abc': Entity{key=Key{projectId=datastore-playground-040718, namespace=, path=[PathElement{kind=ABC, id=null, name=abc}]}, properties={a=StringValue{valueType=STRING, excludeFromIndexes=false, meaning=0, value=A}, b=LongValue{valueType=LONG, excludeFromIndexes=false, meaning=0, value=42}, c=BooleanValue{valueType=BOOLEAN, excludeFromIndexes=false, meaning=0, value=false}}}
```

Reading the stuff we had written manually worked.

Let's see writing.

### MainWrite

tbd.

### MainWatch

<font color=red>There does not seem to be a way to observe changes to Google Cloud Datastore, in a streamed way.</font>

This would still be fine - we can do e.g. once a second polling for new information, using the "cursor" mechanism. 


### History

<font color=red>Something mentioned "version" information, but in practise it does not seem to be there. If we cannot read the history of the data store, we don't get auditing. Less incentive to use Google Cloud Datastore. 
</font>

We could bypass this by simply storing the events in the Datastore, never writing over the existing stuff. But it deviates the view seen natively in the store from the abstraction it's providing. It doesn't feel right.


### Authentication

tbd.

### MainDelete

tbd.


## References

- [Google Cloud Datastore Overview](https://cloud.google.com/datastore/docs/concepts/overview) (Google manuals)

- [Google App Engine Standard Environment Samples for Java 8](https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/appengine-java8) (GitHub)

  - especially [datastore](https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/appengine-java8/datastore) sample

  