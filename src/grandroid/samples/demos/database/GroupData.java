package grandroid.samples.demos.database;

import grandroid.database.Identifiable;
import grandroid.database.Table;

@Table("GroupData")
public class GroupData implements Identifiable {

    protected Integer _id;
    protected String name;
    protected String team;

  

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    

}