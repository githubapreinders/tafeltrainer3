package afr.tafeltrainer3.client.events;

import java.io.Serializable;

import afr.tafeltrainer3.shared.SuperUser;

public class EventAddSuperUser extends DataEvent implements Serializable
{

private static final long serialVersionUID = 5725832439319502130L;

SuperUser superuser;

public SuperUser getSuperuser() {
	return superuser;
}

public void setSuperuser(SuperUser superuser) {
	this.superuser = superuser;
}


}
