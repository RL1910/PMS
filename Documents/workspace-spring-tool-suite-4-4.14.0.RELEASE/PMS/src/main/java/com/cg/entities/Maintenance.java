package com.cg.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Maintenance {
	
	
        @Id
		long id ;
		
        @NotEmpty
		String complainDescription;
		
		@NotNull(message = "Prize should be provided")
		double prize;
		
		@NotNull
		long propertyId;
		

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getComplainDescription() {
			return complainDescription;
		}

		public void setComplainDescription(String complainDescription) {
			this.complainDescription = complainDescription;
		}

		public double getPrize() {
			return prize;
		}

		public void setPrize( double prize) {
			this.prize = prize;
		}

		public long getPropertyId() {
			return propertyId;
		}

		public void setPropertyId(long propertyId) {
			this.propertyId = propertyId;
		}

		@Override
		public String toString() {
			return "Maintenance [id=" + id + ", complainDescription=" + complainDescription + ", prize=" + prize
					+ ", propertyId=" + propertyId + "]";
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
