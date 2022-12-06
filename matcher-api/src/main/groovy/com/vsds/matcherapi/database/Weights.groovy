package com.vsds.matcherapi.database

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "Weights")
class Weights {
    @Id
    ObjectId weight_id
    @Field
    ArrayList<Integer> weights

    Weights(ObjectId weight_id, ArrayList<Integer> weights) {
        this.weight_id = weight_id
        this.weights = weights
    }

    Weights(ArrayList<Integer> weights) {
        this.weights = weights
    }





}
