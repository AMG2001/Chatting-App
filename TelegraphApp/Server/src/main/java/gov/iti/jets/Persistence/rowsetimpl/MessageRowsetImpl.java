package gov.iti.jets.Persistence.rowsetimpl;

import gov.iti.jets.Domain.Message;
import gov.iti.jets.Persistence.dao.MessageDao;
import gov.iti.jets.Persistence.rowsetimpl.conversationRowset.ConversationCacheRowset;
import gov.iti.jets.Persistence.rowsetimpl.messageRowset.MessageCacheRowset;
import gov.iti.jets.Persistence.rowsetimpl.userRowset.UserCacheRowset;
import gov.iti.jets.Persistence.LocalDatabaseServices;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageRowsetImpl implements MessageDao {


    @Override
    public void add(Message entity)
    {try {
        CachedRowSet rowset = gov.iti.jets.Persistence.rowsetimpl.messageRowset.MessageCacheRowset.getInstance().getCacheRowsetObj();
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
            CachedRowSet rowset = gov.iti.jets.Persistence.rowsetimpl.messageRowset.MessageCacheRowset.getInstance().getCacheRowsetObj();
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
                System.out.println(e.getMessage());
            }
            return list;
    }

    @Override
    public void update(Message entity)
    {
        CachedRowSet rowset = gov.iti.jets.Persistence.rowsetimpl.messageRowset.MessageCacheRowset.getInstance().getCacheRowsetObj();
        try {
            rowset.absolute(rowset.findColumn("message_id"));
            while (rowset.next()) {
                if (entity.getMessageId() == rowset.getInt("message_id")) {
                    rowset.updateInt(1, entity.getMessageId());
                    rowset.updateInt(2, entity.getConversationId());
                    rowset.updateString(3, entity.getSenderPhone());
                    rowset.updateInt(4, entity.getAttachmentId());
                    rowset.updateString(5, entity.getMessageBody());
                    rowset.updateTime(6, entity.getTimeStamp());
                    rowset.updateRow();
                    break;
                }
            }
        }catch(SQLException e)
            {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

    }

    @Override
    public void delete(Message message) {
        try {
            CachedRowSet rowset = MessageCacheRowset.getInstance().getCacheRowsetObj();
            rowset.absolute(rowset.findColumn("message_id"));
            while (rowset.next()) {
                if (message.getMessageId()== rowset.getInt("message_id")) {
                    rowset.deleteRow();
                    break;
                }
            }
            rowset.acceptChanges(LocalDatabaseServices.getConnectionObj());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Message> getMessagesByConversationId(String conversationId) {
        return null;
    }
}

