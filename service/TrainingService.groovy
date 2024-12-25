def training = ec.entity.makeValue("MoquiTraining")
training.setFields(context, true, null, null)
training.setSequencedIdPrimary()
training.create()