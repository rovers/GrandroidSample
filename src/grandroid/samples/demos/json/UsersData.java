/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandroid.samples.demos.json;

import grandroid.database.Identifiable;
import grandroid.database.Table;

@Table("UsersData")
public class UsersData implements Identifiable {

    protected Integer _id;
    protected String userId;
    protected String name;
    protected String age;
    protected String team;


    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
       this._id = _id;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

   
}
