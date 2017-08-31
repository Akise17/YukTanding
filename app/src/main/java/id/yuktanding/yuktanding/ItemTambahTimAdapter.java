package id.yuktanding.yuktanding;

/**
 * Created by Akise on 31/08/2017.
 */

public class ItemTambahTimAdapter {
    String userID, namaTim, userIdGk, userIdAnchor, userIdLeftFlank, userIdRightFlank, userIdPivot, daerah, olahraga;

    public ItemTambahTimAdapter(String userID, String namaTim, String userIdGk, String userIdAnchor, String userIdLeftFlank, String userIdRightFlank, String userIdPivot, String daerah, String olahraga) {
        this.userID = userID;
        this.namaTim = namaTim;
        this.userIdGk = userIdGk;
        this.userIdAnchor = userIdAnchor;
        this.userIdLeftFlank = userIdLeftFlank;
        this.userIdRightFlank = userIdRightFlank;
        this.userIdPivot = userIdPivot;
        this.daerah = daerah;
        this.olahraga = olahraga;
    }

    public String getUserID() {
        return userID;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public String getUserIdGk() {
        return userIdGk;
    }

    public String getUserIdAnchor() {
        return userIdAnchor;
    }

    public String getUserIdLeftFlank() {
        return userIdLeftFlank;
    }

    public String getUserIdRightFlank() {
        return userIdRightFlank;
    }

    public String getUserIdPivot() {
        return userIdPivot;
    }

    public String getDaerah() {
        return daerah;
    }

    public String getOlahraga() {
        return olahraga;
    }
}
