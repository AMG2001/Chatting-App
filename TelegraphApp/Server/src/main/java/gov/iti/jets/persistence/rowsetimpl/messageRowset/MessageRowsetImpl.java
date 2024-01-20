package gov.iti.jets.persistence.rowsetimpl.messageRowset;

import gov.iti.jets.domain.Message;
import gov.iti.jets.persistence.dao.MessageDao;
import gov.iti.jets.persistence.rowsetimpl.RowsetFactory;
import gov.iti.jets.services.database.LocalDatabaseServices;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageRowsetImpl implements MessageDao {


    @Override
    public void add(Message entity)
    {try {
        CachedRowSet rowset = RowsetFactory.messageCachedRowsetObj;
        RowsetFactory.messageCachedRowsetObj.moveToInsertRow();
        rowset.updateString(1, entity.getMessageId());
        rowset.updateString(2, entity.getConversationId());
        rowset.updateString(3, entity.getSenderPhone());
        rowset.updateString(4, entity.getAttachmentId());
        rowset.updateString(5, entity.getMessageBody());
        rowset.updateTime(6, entity.getTimeStamp());
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
    public List<Message> getAll()
    {
        List<Message> list = new ArrayList<Message>();
        CachedRowSet rowset = RowsetFactory.messageCachedRowsetObj;

        rowset.setCommand();
    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public void delete(Message message) {

    }

    @Override
    public List<Message> getMessagesByConversationId(String conversationId) {
        return null;
    }
}

