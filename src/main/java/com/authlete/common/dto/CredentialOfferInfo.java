/*
 * Copyright (C) 2023 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.authlete.common.dto;


import java.io.Serializable;
import java.net.URI;


/**
 * Information about a credential offer.
 *
 * @since 3.59
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialOfferInfo implements Serializable
{
    private static final long serialVersionUID = 1L;


    /**
     * The identifier of the credential offer.
     */
    private String identifier;


    /**
     * The credential offer in the JSON format.
     */
    private String credentialOffer;


    /**
     * The identifier of the credential issuer.
     *
     * @since 3.60
     */
    private URI credentialIssuer;


    /**
     * The value of the {@code credentials} object in the JSON format.
     */
    private String credentials;


    /**
     * The flag indicating whether the {@code authorization_code} object is
     * included in the {@code grants} object.
     */
    private boolean authorizationCodeGrantIncluded;


    /**
     * The flag indicating whether the {@code issuer_state} property is
     * included in the {@code authorization_code} object in the {@code grants}
     * object.
     */
    private boolean issuerStateIncluded;


    /**
     * The value of the {@code issuer_state} property in the
     * {@code authorization_code} object in the {@code grants} object.
     */
    private String issuerState;


    /**
     * The flag indicating whether the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object is
     * included in the {@code grants} object.
     */
    private boolean preAuthorizedCodeGrantIncluded;


    /**
     * The value of the {@code pre-authorized_code} property in the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object in
     * the {@code grants} object.
     */
    private String preAuthorizedCode;


    /**
     * The value of the {@code user_pin_required} property in the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object in
     * the {@code grants} object.
     */
    private boolean userPinRequired;


    /**
     * The value of the user PIN associated with the credential offer.
     */
    private String userPin;


    /**
     * The subject associated with the credential offer.
     */
    private String subject;


    /**
     * The time at which the credential offer will expire.
     */
    private long expiresAt;


    /**
     * The general-purpose arbitrary string.
     */
    private String context;


    /**
     * Get the identifier of the credential offer.
     *
     * <p>
     * The identifier is a base64url string with 256-bit entropy consisting of
     * 43 characters.
     * </p>
     *
     * @return
     *         The identifier of the credential offer.
     */
    public String getIdentifier()
    {
        return identifier;
    }


    /**
     * Set the identifier of the credential offer.
     *
     * <p>
     * The identifier is a base64url string with 256-bit entropy consisting of
     * 43 characters.
     * </p>
     *
     * @param identifier
     *         The identifier of the credential offer.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setIdentifier(String identifier)
    {
        this.identifier = identifier;

        return this;
    }


    /**
     * Get the credential offer in the JSON format.
     *
     * <p>
     * The value is suitable for use as the value of the {@code credential_offer}
     * parameter which is sent to the credential offer endpoint of the wallet.
     * It is also suitable as the message body of a response returned from the
     * endpoint designated by the {@code credential_offer_uri} parameter.
     * </p>
     *
     * <p>
     * The credential offer holds JSON like below.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     "authorization_code": {
     *       "issuer_state": "..."
     *     },
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       "user_pin_required": true
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         The credential offer in the JSON format.
     */
    public String getCredentialOffer()
    {
        return credentialOffer;
    }


    /**
     * Set the credential offer in the JSON format.
     *
     * <p>
     * The value is suitable for use as the value of the {@code credential_offer}
     * parameter which is sent to the credential offer endpoint of the wallet.
     * It is also suitable as the message body of a response returned from the
     * endpoint designated by the {@code credential_offer_uri} parameter.
     * </p>
     *
     * <p>
     * The credential offer should hold JSON like below.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     "authorization_code": {
     *       "issuer_state": "..."
     *     },
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       "user_pin_required": true
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @param offer
     *         The credential offer in the JSON format.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setCredentialOffer(String offer)
    {
        this.credentialOffer = offer;

        return this;
    }


    /**
     * Get the identifier of the credential issuer.
     *
     * <blockquote>
     * <pre>
     * {
     *   <span style="color:darkred;">"credential_issuer": "..."</span>,
     *   "credentials": [ ... ],
     *   "grants": { ... }
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         The identifier of the credential issuer.
     *
     * @since 3.60
     */
    public URI getCredentialIssuer()
    {
        return credentialIssuer;
    }


    /**
     * Set the identifier of the credential issuer.
     *
     * <blockquote>
     * <pre>
     * {
     *   <span style="color:darkred;">"credential_issuer": "..."</span>,
     *   "credentials": [ ... ],
     *   "grants": { ... }
     * }
     * </pre>
     * </blockquote>
     *
     * @param issuer
     *         The identifier of the credential issuer.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.60
     */
    public CredentialOfferInfo setCredentialIssuer(URI issuer)
    {
        this.credentialIssuer = issuer;

        return this;
    }


    /**
     * Get the value of the {@code credentials} property of the credential
     * offer in the JSON format.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   <span style="color:darkred;">"credentials": [ ... ]</span>,
     *   "grants": { ... }
     * }
     * </pre>
     * </blockquote>
     *
     * <p>
     * The value represents a JSON array.
     * </p>
     *
     * @return
     *         The value of the {@code credentials} property of the credential
     *         offer in the JSON format.
     */
    public String getCredentials()
    {
        return credentials;
    }


    /**
     * Set the value of the {@code credentials} property of the credential
     * offer in the JSON format.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   <span style="color:darkred;">"credentials": [ ... ]</span>,
     *   "grants": { ... }
     * }
     * </pre>
     * </blockquote>
     *
     * <p>
     * The value should represent a JSON array.
     * </p>
     *
     * @param credentials
     *         The value of the {@code credentials} property of the credential
     *         offer in the JSON format.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setCredentials(String credentials)
    {
        this.credentials = credentials;

        return this;
    }


    /**
     * Get the flag indicating whether the {@code authorization_code} object is
     * included in the {@code grants} object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     <span style="color:darkred;">"authorization_code"</span>: { ... }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         {@code true} if the {@code authorization_code} object is
     *         included in the {@code grants} object.
     */
    public boolean isAuthorizationCodeGrantIncluded()
    {
        return authorizationCodeGrantIncluded;
    }


    /**
     * Set the flag indicating whether the {@code authorization_code} object is
     * included in the {@code grants} object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     <span style="color:darkred;">"authorization_code"</span>: { ... }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @param included
     *         {@code true} to indicate that the {@code authorization_code}
     *         object is included in the {@code grants} object.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setAuthorizationCodeGrantIncluded(boolean included)
    {
        this.authorizationCodeGrantIncluded = included;

        return this;
    }


    /**
     * Get the flag indicating whether the {@code issuer_state} property is
     * included in the {@code authorization_code} object in the {@code grants}
     * object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     "authorization_code": {
     *       <span style="color:darkred;">"issuer_state"</span>: "..."
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         {@code true} if the {@code issuer_state} property is included
     *         in the {@code authorization_code} object in the {@code grants}
     *         object.
     */
    public boolean isIssuerStateIncluded()
    {
        return issuerStateIncluded;
    }


    /**
     * Set the flag indicating whether the {@code issuer_state} property is
     * included in the {@code authorization_code} object in the {@code grants}
     * object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     "authorization_code": {
     *       <span style="color:darkred;">"issuer_state"</span>: "..."
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @param included
     *         {@code true} to indicate that the {@code issuer_state} property
     *         is included in the {@code authorization_code} object in the
     *         {@code grants} object.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setIssuerStateIncluded(boolean included)
    {
        this.issuerStateIncluded = included;

        return this;
    }


    /**
     * Get the value of the {@code issuer_state} property in the
     * {@code authorization_code} object in the {@code grants} object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     "authorization_code": {
     *       <span style="color:darkred;">"issuer_state": "..."</span>
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         The value of the {@code issuer_state} property in the
     *         {@code authorization_code} object in the {@code grants}
     *         object.
     */
    public String getIssuerState()
    {
        return issuerState;
    }


    /**
     * Set the value of the {@code issuer_state} property in the
     * {@code authorization_code} object in the {@code grants} object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     "authorization_code": {
     *       <span style="color:darkred;">"issuer_state": "..."</span>
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @param state
     *         The value of the {@code issuer_state} property in the
     *         {@code authorization_code} object in the {@code grants}
     *         object.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setIssuerState(String state)
    {
        this.issuerState = state;

        return this;
    }


    /**
     * Get the flag indicating whether the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object is
     * included in the {@code grants} object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     <span style="color:darkred;">"urn:ietf:params:oauth:grant-type:pre-authorized_code"</span>: { ... }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         {@code true} if the
     *         {@code urn:ietf:params:oauth:grant-type:pre-authorized_code}
     *         object is included in the {@code grants} object.
     */
    public boolean isPreAuthorizedCodeGrantIncluded()
    {
        return preAuthorizedCodeGrantIncluded;
    }


    /**
     * Set the flag indicating whether the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object is
     * included in the {@code grants} object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     <span style="color:darkred;">"urn:ietf:params:oauth:grant-type:pre-authorized_code"</span>: { ... }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @param included
     *         {@code true} to indicate that the
     *         {@code urn:ietf:params:oauth:grant-type:pre-authorized_code}
     *         object is included in the {@code grants} object.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setPreAuthorizedCodeGrantIncluded(boolean included)
    {
        this.preAuthorizedCodeGrantIncluded = included;

        return this;
    }


    /**
     * Get the value of the {@code pre-authorized_code} property in the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object in
     * the {@code grants} object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       <span style="color:darkred;">"pre-authorized_code": "..."</span>
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @return
     *         The value of the {@code pre-authorized_code} property in the
     *         {@code urn:ietf:params:oauth:grant-type:pre-authorized_code}
     *         object in the {@code grants} object.
     */
    public String getPreAuthorizedCode()
    {
        return preAuthorizedCode;
    }


    /**
     * Set the value of the {@code pre-authorized_code} property in the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object in
     * the {@code grants} object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       <span style="color:darkred;">"pre-authorized_code": "..."</span>
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @param code
     *         The value of the {@code pre-authorized_code} property in the
     *         {@code urn:ietf:params:oauth:grant-type:pre-authorized_code}
     *         object in the {@code grants} object.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setPreAuthorizedCode(String code)
    {
        this.preAuthorizedCode = code;

        return this;
    }


    /**
     * Get the value of the {@code user_pin_required} property in the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object in
     * the {@code grants} object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       <span style="color:darkred;">"user_pin_required": true</span>
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * <p>
     * Note that this method returns {@code false} also in the case where the
     * {@code user_pin_required} property is not included.
     * </p>
     *
     * @return
     *         The value of the {@code user_pin_required} property in the
     *         {@code urn:ietf:params:oauth:grant-type:pre-authorized_code}
     *         object in the {@code grants} object.
     */
    public boolean isUserPinRequired()
    {
        return userPinRequired;
    }


    /**
     * Set the value of the {@code user_pin_required} property in the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object in
     * the {@code grants} object.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credentials": [ ... ],
     *   "grants": {
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       <span style="color:darkred;">"user_pin_required": true</span>
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @param required
     *         {@code true} to indicate that the value of the
     *         {@code user_pin_required} property is {@code true}.
     *         {@code false} to indicate that the value of the property
     *         is {@code false} or that the property is missing.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setUserPinRequired(boolean required)
    {
        this.userPinRequired = required;

        return this;
    }


    /**
     * Get the value of the user PIN associated with the credential offer.
     *
     * <p>
     * The value consists of maximum 8 numeric characters. For example,
     * {@code 493536}.
     * </p>
     *
     * @return
     *         The value of the user PIN.
     */
    public String getUserPin()
    {
        return userPin;
    }


    /**
     * Set the value of the user PIN associated with the credential offer.
     *
     * <p>
     * The value consists of maximum 8 numeric characters. For example,
     * {@code 493536}.
     * </p>
     *
     * @param pin
     *         The value of the user PIN.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setUserPin(String pin)
    {
        this.userPin = pin;

        return this;
    }


    /**
     * Get the subject associated with the credential offer.
     *
     * <p>
     * This property holds the value specified by the {@code subject} request
     * parameter passed to the {@code /vci/offer/create} API.
     * </p>
     *
     * @return
     *         The value of the subject.
     *
     * @see CredentialOfferCreateRequest#getSubject()
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject associated with the credential offer.
     *
     * <p>
     * This property should hold the value specified by the {@code subject}
     * request parameter passed to the {@code /vci/offer/create} API.
     * </p>
     *
     * @param subject
     *         The value of the subject.
     *
     * @return
     *         {@code this} object.
     *
     * @see CredentialOfferCreateRequest#setSubject(String)
     */
    public CredentialOfferInfo setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the time at which the credential offer will expire.
     *
     * @return
     *         The time at which the credential offer will expire.
     *         The value represents milliseconds elapsed since the Unix epoch.
     */
    public long getExpiresAt()
    {
        return expiresAt;
    }


    /**
     * Set the time at which the credential offer will expire.
     *
     * @param expiresAt
     *         The time at which the credential offer will expire.
     *         The value represents milliseconds elapsed since the Unix epoch.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferInfo setExpiresAt(long expiresAt)
    {
        this.expiresAt = expiresAt;

        return this;
    }


    /**
     * Get the general-purpose arbitrary string.
     *
     * <p>
     * This property holds the value specified by the {@code context} request
     * parameter passed to the {@code /vci/offer/create} API.
     * </p>
     *
     * @return
     *         The general-purpose arbitrary string.
     *
     * @see CredentialOfferCreateRequest#getContext()
     */
    public String getContext()
    {
        return context;
    }


    /**
     * Set the general-purpose arbitrary string.
     *
     * <p>
     * This property should hold the value specified by the {@code context}
     * request parameter passed to the {@code /vci/offer/create} API.
     * </p>
     *
     * @param context
     *         The general-purpose arbitrary string.
     *
     * @return
     *         {@code this} object.
     *
     * @see CredentialOfferCreateRequest#setContext(String)
     */
    public CredentialOfferInfo setContext(String context)
    {
        this.context = context;

        return this;
    }
}
