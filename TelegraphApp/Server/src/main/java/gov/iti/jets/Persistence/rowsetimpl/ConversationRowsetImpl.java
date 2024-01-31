package gov.iti.jets.Persistence.rowsetimpl;

import gov.iti.jets.Domain.Conversation;
import gov.iti.jets.Persistence.dao.ConversationDao;
import gov.iti.jets.Persistence.LocalDatabaseServices;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.List;

public class ConversationRowsetImpl implements ConversationDao {
    @Override
    public List<Conversation> getConversationsByPhone(String phone) {
        return null;
    }
    @Override
    public void add(Conversation entity) {
        try {
            CachedRowSet rowset = CustomCacheRowsets.getAttachmentCacheRowset();
            rowset.moveToInsertRow();
            rowset.updateInt(1, entity.getConversationId());
            rowset.updateString(2, entity.getConversationImage());
            rowset.updateString(3, entity.getType());
            rowset.insertRow();
            rowset.moveToCurrentRow();
            rowset.acceptChanges(LocalDatabaseServices.getConnectionObj());
            rowset.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<Conversation> getAll() {
        return null;
    }

    @Override
    public void update(Conversation entity) {

    }

    @Override
    public void delete(Conversation entity) {

    }

    @Override
    public Conversation getById(String s) {
        return null;
    }
}

