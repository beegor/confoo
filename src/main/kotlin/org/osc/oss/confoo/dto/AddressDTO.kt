package org.osc.oss.confoo.dto

import org.osc.oss.confoo.core.Address

class AddressDTO(var street: String,
                 var city: String,
                 var country: String) {

    companion object {
        fun empty(): AddressDTO {
            return AddressDTO("", "", "")
        }
    }

    constructor(address: Address) : this(address.street, address.city, address.country)

    fun toAddress() = Address(street, city, country)
}