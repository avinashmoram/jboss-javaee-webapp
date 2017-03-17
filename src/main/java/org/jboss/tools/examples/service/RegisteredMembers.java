/************************************************************
 * TEXAS COMMISSION ON ENVIRONMENTAL QUALITY (TCEQ)         *
 * PERMIT AND REGISTRATION INFORMATION SYSTEM (PARIS)       *
 * Copyright(c)2010 - All Rights Reserved.                  *
 * This software is the proprietary information of The TCEQ *
 ************************************************************/
package org.jboss.tools.examples.service;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.scope.ConversationGroup;
import org.apache.deltaspike.core.api.scope.GroupedConversation;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;
import org.apache.deltaspike.core.spi.scope.conversation.GroupedConversationManager;
import org.apache.deltaspike.core.spi.scope.window.WindowContext;
import org.jboss.tools.examples.context.Context;
import org.jboss.tools.examples.context.Contexts;
import org.jboss.tools.examples.data.MemberRepository;
import org.jboss.tools.examples.model.Member;

import or.jboss.tools.examples.groups.ConversationWQGroup;

/**<b>Author:</b>	Avinash Moram
 *<p><b>Date:</b>	03/16/2017
 *
 *<p><b>PAGE DESCRIPTION:
 *==================================================================================</b>
 *
 *
 *<p><b>CHANGE LOG:
 *==================================================================================
 *Date			UserName	PhoneEx.	- Comment </b><p>
 *03/16/2017	AMORAM		x0821		Initial creation
 *
 */

@Model
@GroupedConversationScoped
@ConversationGroup(ConversationWQGroup.class)
public class RegisteredMembers implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
    private Logger log;

	private Long id;

	private Member member;

	@Inject
	private GroupedConversation conversation;

	@Inject
    private WindowContext windowContext;

	@Inject
    private MemberRepository repository;

	@Inject
    @Context
    private Contexts contexts;

	@Inject
	private GroupedConversationManager conversationManager;

    public String members(Long id) throws Exception {
        log.info("Retrieving member by Id:" + id);
        Member member = repository.findById(id);
        this.member = member;
        return "displayMembers.xhtml?faces-redirect=true";
    }

    public String endConversation(){
    	this.conversation.close();
    	return "index.xhtml?faces-redirect=true";
    }

    public String finish(){
    	this.conversationManager.closeConversationGroup(ConversationWQGroup.class);
    	return "/index.xhtml?faces-redirect=true";
    }

    public String windowid(){
    	return this.windowContext.getCurrentWindowId();
    }

    public Long getId() {
    	return id;
    }

    public void setId(Long id) {
    	this.id = id;
    }

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
