package gov.iti.jets.persistence.rowsetimpl.messageRowset;

import gov.iti.jets.domain.Message;
import gov.iti.jets.persistence.dao.MessageDao;
import gov.iti.jets.persistence.rowsetimpl.RowsetFactory;
import gov.iti.jets.persistence.rowsetimpl.conversationRowset.ConversationCacheRowset;
import gov.iti.jets.services.database.LocalDatabaseServices;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageRowsetImpl implements MessageDao {


    @Override
    public void add(Message entity)
    {try {
        CachedRowSet rowset = MessageCacheRowset.getInstance().getCacheRowsetObj();
        rowset.moveToInsertRow();
        rowset.updateInt(1, entity.getMessageId());
        rowset.updateInt(2, entity.getConversationId());
        rowset.updateString(3, entity.getSenderPhone());
        rowset.updateInt(4, entity.getAttachmentId());
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
            CachedRowSet rowset = MessageCacheRowset.getInstance().getCacheRowsetObj();
            try {
                rowset.beforeFirst();
                while (rowset.next()) {
                    Message message = new Message();
                    message.setMessageId(rowset.getInt(1));
                    message.setConversationId(rowset.getInt(2));
                    message.setSenderPhone(rowset.getString(3));
                    message.setAttachmentId(rowset.getInt(4));
                    message.setMessageBody(rowset.getString(5));
                    message.setTimeStamp(rowset.getTime(6));
                    list.add(message);
                }
            }catch(SQLException e)
            {
                e.printStackTrace();
            }
            return list;
    }

    @Override
    public void update(Message entity)
    {
        CachedRowSet rowset = MessageCacheRowset.getInstance().getCacheRowsetObj();
        rowset.absolute(entity.getRow);

    }

    @Override
    public void delete(Message message) {

    }

    @Override
    public List<Message> getMessagesByConversationId(String conversationId) {
        return null;
    }
}

