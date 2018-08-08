package com.codecool.web.service;

import com.codecool.web.Utility;
import com.codecool.web.dto.Contact;
import com.codecool.web.dto.MessageDto;
import com.codecool.web.model.Ad;
import com.codecool.web.model.Message;
import com.codecool.web.model.User;
import com.codecool.web.repository.AdRepository;
import com.codecool.web.repository.MessageRepository;
import com.codecool.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    public List<Message> getAllBySender_IdAndReceiver_IdOrderByTimestampAsc(int senderId, int receiverId) {
        return messageRepository.findAllBySender_IdAndReceiver_IdOrderByTimestampAsc(senderId, receiverId);
    }

    public List<Message> getAllBySender_IdAndReceiver_IdAndTextContainingOrderByTimestampAsc(int senderId, int receiverId, String keyword) {
        return messageRepository.findAllBySender_IdAndReceiver_IdAndTextContainingOrderByTimestampAsc(senderId, receiverId, keyword);
    }

    public MessageDto addNewMessage(MessageDto messageDto) {
        User sender = userRepository.findById(messageDto.getSenderId());
        User receiver = userRepository.findById(messageDto.getReceiverId());
        Ad ad = adRepository.findById(messageDto.getAdId());
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        messageDto.setTimestamp(new Timestamp(new Date().getTime()).toLocalDateTime());
        Message message = new Message(messageDto, sender, receiver, ad);
        messageRepository.save(message);
        return new MessageDto(message);
    }

    public List<Contact> getContactsByUserId(int userId) {
        List<Contact> contacts = new ArrayList<>();
        List<Message> messages = messageRepository.findDistinctBySender_IdOrReceiver_IdOrderByTimestampAsc(userId, userId);
        Set<User> users = Utility.convertMessagesToUserSet(messages, userId);
        for (User user : users) {
            List<Message> sentMessages = messageRepository.findAllBySender_IdAndReceiver_IdOrderByTimestampAsc(userId, user.getId());
            List<Message> receivedMessages = messageRepository.findAllBySender_IdAndReceiver_IdOrderByTimestampAsc(user.getId(), userId);
            receivedMessages.addAll(sentMessages);
            Collections.sort(receivedMessages, Comparator.comparing(Message::getTimestamp));
            List<MessageDto> messageDtos = Utility.convertMessageListtoMessageDtoList(receivedMessages);
            Ad ad = receivedMessages.get(0).getAd();
            Contact contact = new Contact(user, messageDtos, messageDtos.get(messageDtos.size() - 1), ad);
            contacts.add(contact);
        }
        return contacts;
    }

}
