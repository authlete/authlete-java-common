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
 * <h3>Breaking Changes</h3>
 *
 * <p>
 * The "OpenID for Verifiable Credential Issuance" specification tends to
 * repeat breaking changes. Such changes affect this Java class.
 * </p>
 *
 * <p>
 * In the past draft of the specification, elements in the "{@code credentials}"
 * array in a credential offer are either strings or JSON objects. Therefore, the
 * type of the "{@code credentials}" property in the previous implementation of
 * this class was a "string" whose content must be able to be parsed as a JSON
 * array. However, as a result of a breaking change in the specification, it is
 * ensured that all elements in the "{@code credentials}" array in a credential
 * offer are strings. To make it easier to treat the "{@code credentials}"
 * property of this class, the type of the property has been changed from a
 * string to an array of strings.
 * </p>
 *
 * <p>
 * Due to another breaking change made in December 2023, the {@code credentials}
 * property in a credential offer has been renamed to
 * {@code credential_configurations}. In addition, the {@code user_pin_required}
 * boolean property has been replaced with the {@code tx_code} JSON object.
 * </p>
 *
 * @since 3.59
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialOfferInfo implements Serializable
{
    private static final long serialVersionUID = 4L;


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
     * The value of the {@code credential_configurations} array.
     *
     * @since 3.91
     */
    private String[] credentialConfigurations;


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
     * Extra properties to associate with the credential offer.
     *
     * @since 3.62
     */
    private Property[] properties;


    /**
     * Additional claims that are added to the payload part of the JWT
     * access token.
     *
     * @since 3.62
     */
    private String jwtAtClaims;


    /**
     * The time at which the user authentication was performed during
     * the course of issuing the credential offer.
     *
     * @since 3.62
     */
    private long authTime;


    /**
     * The Authentication Context Class Reference of the user authentication
     * performed during the course of issuing the credential offer.
     *
     * @since 3.62
     */
    private String acr;


    /**
     * The transaction code.
     *
     * @since 3.91
     */
    private String txCode;


    /**
     * The input mode of the transaction code.
     *
     * @since 3.91
     */
    private String txCodeInputMode;


    /**
     * The description of the transaction code.
     *
     * @since 3.91
     */
    private String txCodeDescription;


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
     *   "credential_configurations": [ ... ],
     *   "grants": {
     *     "authorization_code": {
     *       "issuer_state": "..."
     *     },
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       "tx_code": {
     *         "input_mode": "numeric",
     *         "length": 6,
     *         "description": "..."
     *       }
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
     *   "credential_configurations": [ ... ],
     *   "grants": {
     *     "authorization_code": {
     *       "issuer_state": "..."
     *     },
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       "tx_code": {
     *         "input_mode": "numeric",
     *         "length": 6,
     *         "description": "..."
     *       }
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
     *   "credential_configurations": [ ... ],
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
     *   "credential_configurations": [ ... ],
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
     * Get the value of the {@code credential_configurations} property of
     * the credential offer.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   <span style="color:darkred;">"credential_configurations": [ ... ]</span>,
     *   "grants": { ... }
     * }
     * </pre>
     * </blockquote>
     *
     * <p>
     * The type of the object returned from this method has been changed
     * from {@code String} to {@code String[]} since version 3.86 to align
     * with the breaking change of the OID4VCI specification.
     * </p>
     *
     * <p>
     * NOTE: Due to the breaking change made in December 2023, the
     * {@code credentials} property in a credential offer has been renamed to
     * {@code credential_configurations}.
     * </p>
     *
     * @return
     *         The value of the {@code credential_configurations} property of
     *         the credential offer.
     *
     * @since 3.91
     */
    public String[] getCredentialConfigurations()
    {
        return credentialConfigurations;
    }


    /**
     * Set the value of the {@code credential_configurations} property of
     * the credential offer.
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   <span style="color:darkred;">"credential_configurations": [ ... ]</span>,
     *   "grants": { ... }
     * }
     * </pre>
     * </blockquote>
     *
     * <p>
     * NOTE: Due to the breaking change made in December 2023, the
     * {@code credentials} property in a credential offer has been renamed to
     * {@code credential_configurations}.
     * </p>
     *
     * @param configurations
     *         The value of the {@code credential_configurations} property of
     *         the credential offer.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.91
     */
    public CredentialOfferInfo setCredentialConfigurations(String[] configurations)
    {
        this.credentialConfigurations = configurations;

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
     *   "credential_configurations": [ ... ],
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
     *   "credential_configurations": [ ... ],
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
     *   "credential_configurations": [ ... ],
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
     *   "credential_configurations": [ ... ],
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
     *   "credential_configurations": [ ... ],
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
     *   "credential_configurations": [ ... ],
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
     *   "credential_configurations": [ ... ],
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
     *   "credential_configurations": [ ... ],
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
     *   "credential_configurations": [ ... ],
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
     *   "credential_configurations": [ ... ],
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


    /**
     * Get the extra properties associated with the credential offer.
     * Extra properties are general-purpose key-value pairs.
     *
     * <p>
     * The extra properties will be eventually associated with an access token
     * which will be created based on the credential offer.
     * </p>
     *
     * @return
     *         The extra properties associated with the credential offer.
     *
     * @since 3.62
     */
    public Property[] getProperties()
    {
        return properties;
    }


    /**
     * Set the extra properties associated with the credential offer.
     * Extra properties are general-purpose key-value pairs.
     *
     * <p>
     * The extra properties will be eventually associated with an access token
     * which will be created based on the credential offer.
     * </p>
     *
     *
     * @param properties
     *         The extra properties associated with the credential offer.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.62
     */
    public CredentialOfferInfo setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get the additional claims in JSON object format that are added to the
     * payload part of the JWT access token.
     *
     * <p>
     * This property has a meaning only when the format of access tokens issued
     * by the service is JWT. In other words, it has a meaning only when the
     * {@code accessTokenSignAlg} property of the {@link Service} holds a
     * non-null value. See the description of the {@link
     * Service#getAccessTokenSignAlg() getAccessTokenSignAlg()} method for
     * details.
     * </p>
     *
     * <p>
     * The additional claims will be eventually associated with an access token
     * which will be created based on the credential offer.
     * </p>
     *
     * @return
     *         Additional claims that are added to the payload part of the JWT
     *         access token.
     *
     * @since 3.62
     */
    public String getJwtAtClaims()
    {
        return jwtAtClaims;
    }


    /**
     * Set the additional claims in JSON object format that are added to the
     * payload part of the JWT access token.
     *
     * <p>
     * This property has a meaning only when the format of access tokens issued
     * by the service is JWT. In other words, it has a meaning only when the
     * {@code accessTokenSignAlg} property of the {@link Service} holds a
     * non-null value. See the description of the {@link
     * Service#getAccessTokenSignAlg() getAccessTokenSignAlg()} method for
     * details.
     * </p>
     *
     * <p>
     * The additional claims will be eventually associated with an access token
     * which will be created based on the credential offer.
     * </p>
     *
     * @param claims
     *         Additional claims that are added to the payload part of the JWT
     *         access token.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.62
     */
    public CredentialOfferInfo setJwtAtClaims(String claims)
    {
        this.jwtAtClaims = claims;

        return this;
    }


    /**
     * Get the time when the user authentication was performed during the course
     * of issuing the credential offer.
     *
     * @return
     *         The time of the user authentication in seconds since the Unix epoch.
     *
     * @since 3.62
     */
    public long getAuthTime()
    {
        return authTime;
    }


    /**
     * Set the time when the user authentication was performed during the course
     * of issuing the credential offer.
     *
     * @param authTime
     *         The time of the user authentication in seconds since the Unix epoch.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.62
     */
    public CredentialOfferInfo setAuthTime(long authTime)
    {
        this.authTime = authTime;

        return this;
    }


    /**
     * Get the Authentication Context Class Reference of the user authentication
     * performed during the course of issuing the credential offer.
     *
     * @return
     *         The Authentication Context Class Reference.
     *
     * @since 3.62
     */
    public String getAcr()
    {
        return acr;
    }


    /**
     * Set the Authentication Context Class Reference of the user authentication
     * performed during the course of issuing the credential offer.
     *
     * @param acr
     *         The Authentication Context Class Reference.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.62
     */
    public CredentialOfferInfo setAcr(String acr)
    {
        this.acr = acr;

        return this;
    }


    /**
     * Get the transaction code.
     *
     * @return
     *         The transaction code.
     *
     * @since 3.91
     */
    public String getTxCode()
    {
        return txCode;
    }


    /**
     * Set the transaction code.
     *
     * @param txCode
     *         The transaction code.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.91
     */
    public CredentialOfferInfo setTxCode(String txCode)
    {
        this.txCode = txCode;

        return this;
    }


    /**
     * Get the input mode of the transaction code.
     *
     * @return
     *         The input mode of the transaction code.
     *
     * @since 3.91
     */
    public String getTxCodeInputMode()
    {
        return txCodeInputMode;
    }


    /**
     * Set the input mode of the transaction code.
     *
     * @param inputMode
     *         The input mode of the transaction code.
     *         Such as "{@code numeric}" and "{@code text}".
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.91
     */
    public CredentialOfferInfo setTxCodeInputMode(String inputMode)
    {
        this.txCodeInputMode = inputMode;

        return this;
    }


    /**
     * Get the description of the transaction code.
     *
     * @return
     *         The description of the transaction code.
     *
     * @since 3.91
     */
    public String getTxCodeDescription()
    {
        return txCodeDescription;
    }


    /**
     * Set the description of the transaction code.
     *
     * @param description
     *         The description of the transaction code.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.91
     */
    public CredentialOfferInfo setTxCodeDescription(String description)
    {
        this.txCodeDescription = description;

        return this;
    }
}
