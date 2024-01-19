package gov.iti.jets.persistence.rowsetimpl;

import gov.iti.jets.domain.Conversation;
import gov.iti.jets.persistence.dao.ConversationDao;

import java.util.List;

public class ConversationRowsetImpl implements ConversationDao {

    @Override
    public List<Conversation> getConversationsByPhone(String phone) {
        return null;
    }

    @Override
    public void add(Conversation entity) {

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

