package org.osc.oss.confoo.core

import javax.persistence.Embeddable

@Embeddable
class Address (val street: String,
               val city: String,
               val country:String) {

    companion object {
        fun empty() : Address{
            return Address("", "", "")
        }
    }
}