/*
 * Copyright 2010 Jens Dietrich Licensed under the GNU AFFERO GENERAL PUBLIC LICENSE, Version 3
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.gnu.org/licenses/agpl.html Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package org.mandarax.examples.userv.domain;

/**
 * Bean class that is part of the example domain model.
 * http://www.businessrulesforum.com/2005_Product_Derby.pdf 
 * @author jens dietrich
 */

public class Driver {
	private boolean isMale = true;
	private int age = 18;
	private boolean hasDriversTrainingFromSchool = false;
	private boolean  hasDriversTrainingFromLicensedDriverTrainingCompany = false;
	private boolean hasTakenASeniorCitizenDriversRefresherCourse = false;
	private boolean hasBeenConvictedOfaDUI = false;
	private int numberOfAccidentsInvolvedIn = 0;
	private int numberOfMovingViolationsInLastTwoYears = 0;
	private int numberOfYearsWithUServ = 0;
	private boolean isPreferred = false;
	private boolean isElite = false;
	private String location = "unknown"; // the state, such as CA or NY
	private boolean isMarried = false;
	
	public boolean isMarried() {
		return isMarried;
	}
	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}
	public boolean isMale() {
		return isMale;
	}
	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public boolean hasDriversTrainingFromSchool() {
		return hasDriversTrainingFromSchool;
	}
	public void setHasDriversTrainingFromSchool(boolean hasDriversTrainingFromSchool) {
		this.hasDriversTrainingFromSchool = hasDriversTrainingFromSchool;
	}
	public boolean hasDriversTrainingFromLicensedDriverTrainingCompany() {
		return hasDriversTrainingFromLicensedDriverTrainingCompany;
	}
	public void setHasDriversTrainingFromLicensedDriverTrainingCompany(
			boolean hasDriversTrainingFromLicensedDriverTrainingCompany) {
		this.hasDriversTrainingFromLicensedDriverTrainingCompany = hasDriversTrainingFromLicensedDriverTrainingCompany;
	}
	public boolean hasTakenASeniorCitizenDriversRefresherCourse() {
		return hasTakenASeniorCitizenDriversRefresherCourse;
	}
	public void setHasTakenASeniorCitizenDriversRefresherCourse(
			boolean hasTakenASeniorCitizenDriversRefresherCourse) {
		this.hasTakenASeniorCitizenDriversRefresherCourse = hasTakenASeniorCitizenDriversRefresherCourse;
	}
	public boolean hasBeenConvictedOfaDUI() {
		return hasBeenConvictedOfaDUI;
	}
	public void setHasBeenConvictedOfaDUI(boolean hasBeenConvictedOfaDUI) {
		this.hasBeenConvictedOfaDUI = hasBeenConvictedOfaDUI;
	}
	public int getNumberOfAccidentsInvolvedIn() {
		return numberOfAccidentsInvolvedIn;
	}
	public void setNumberOfAccidentsInvolvedIn(int numberOfAccidentsInvolvedIn) {
		this.numberOfAccidentsInvolvedIn = numberOfAccidentsInvolvedIn;
	}
	public int getNumberOfMovingViolationsInLastTwoYears() {
		return numberOfMovingViolationsInLastTwoYears;
	}
	public void setNumberOfMovingViolationsInLastTwoYears(
			int numberOfMovingViolationsInLastTwoYears) {
		this.numberOfMovingViolationsInLastTwoYears = numberOfMovingViolationsInLastTwoYears;
	}
	public int getNumberOfYearsWithUServ() {
		return numberOfYearsWithUServ;
	}
	public void setNumberOfYearsWithUServ(int numberOfYearsWithUServ) {
		this.numberOfYearsWithUServ = numberOfYearsWithUServ;
	}
	public boolean isPreferred() {
		return isPreferred;
	}
	public void setPreferred(boolean isPreferred) {
		this.isPreferred = isPreferred;
	}
	public boolean isElite() {
		return isElite;
	}
	public void setElite(boolean isElite) {
		this.isElite = isElite;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
