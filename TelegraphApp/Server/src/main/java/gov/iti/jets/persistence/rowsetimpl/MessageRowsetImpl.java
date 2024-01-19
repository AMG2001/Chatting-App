package gov.iti.jets.persistence.rowsetimpl;

import gov.iti.jets.domain.Message;
import gov.iti.jets.persistence.dao.MessageDao;

import java.util.List;

public class MessageRowsetImpl implements MessageDao {


    @Override
    public void add(Message entity) {

    }

    @Override
    public List<Message> getAll() {
        return null;
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

