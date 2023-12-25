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


/**
 * Request to Authlete's {@code /vci/offer/create} API.
 *
 * <p>
 * The API is used to create a <b>credential offer</b>.
 * </p>
 *
 * <p>
 * A credential offer is a JSON object that is used as the value of the
 * {@code credential_offer} parameter sent to the credential offer endpoint
 * of the wallet or is returned from an endpoint designated by the
 * {@code credential_offer_uri} parameter.
 * </p>
 *
 * <p>
 * A credential offer looks like below. Properties in this class (= request
 * parameters to the {@code /vci/offer/create} API) are used to control the
 * content of the credential offer being created.
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
public class CredentialOfferCreateRequest implements Serializable
{
    private static final long serialVersionUID = 4L;


    /**
     * The value of the {@code credential_configurations} array.
     *
     * @since 3.91
     */
    private String[] credentialConfigurations;


    /**
     * The flag to include the {@code authorization_code} object in the
     * {@code grants} object.
     */
    private boolean authorizationCodeGrantIncluded;


    /**
     * The flag to include the {@code issuer_state} property in the
     * {@code authorization_code} object in the {@code grants} object.
     */
    private boolean issuerStateIncluded;


    /**
     * The flag to include the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object
     * in the {@code grants} object.
     */
    private boolean preAuthorizedCodeGrantIncluded;


    /**
     * The subject associated with the credential offer.
     */
    private String subject;


    /**
     * The duration of the credential offer.
     */
    private long duration;


    /**
     * A general-purpose arbitrary string.
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
     * Get the value of the {@code credential_configurations} array.
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
     * This property is mandatory.
     * </p>
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
     *         The value of the {@code credential_configurations} array.
     *
     * @since 3.91
     */
    public String[] getCredentialConfigurations()
    {
        return credentialConfigurations;
    }


    /**
     * Set the value of the {@code credential_configurations} array.
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
     * This property is mandatory.
     * </p>
     *
     * <p>
     * NOTE: Due to the breaking change made in December 2023, the
     * {@code credentials} property in a credential offer has been renamed to
     * {@code credential_configurations}.
     * </p>
     *
     * @param configurations
     *         The value of the {@code credential_configurations} array.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.91
     */
    public CredentialOfferCreateRequest setCredentialConfigurations(String[] configurations)
    {
        this.credentialConfigurations = configurations;

        return this;
    }


    /**
     * Get the flag to include the {@code authorization_code} object in the
     * {@code grants} object.
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
     *         {@code true} if the {@code authorization_code} object will be
     *         included.
     */
    public boolean isAuthorizationCodeGrantIncluded()
    {
        return authorizationCodeGrantIncluded;
    }


    /**
     * Set the flag to include the {@code authorization_code} object in the
     * {@code grants} object.
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
     *         {@code true} to include the {@code authorization_code} object.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferCreateRequest setAuthorizationCodeGrantIncluded(boolean included)
    {
        this.authorizationCodeGrantIncluded = included;

        return this;
    }


    /**
     * Get the flag to include the {@code issuer_state} property in the
     * {@code authorization_code} object in the {@code grants} object.
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
     * <p>
     * When this property is {@code true}, Authlete generates an issuer state
     * and puts it in the {@code authorization_code} object as the value of
     * the {@code issuer_state} property.
     * </p>
     *
     * @return
     *         {@code true} if the {@code issuer_state} property will be
     *         included.
     */
    public boolean isIssuerStateIncluded()
    {
        return issuerStateIncluded;
    }


    /**
     * Set the flag to include the {@code issuer_state} property in the
     * {@code authorization_code} object in the {@code grants} object.
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
     * <p>
     * When this property is {@code true}, Authlete generates an issuer state
     * and puts it in the {@code authorization_code} object as the value of
     * the {@code issuer_state} property.
     * </p>
     *
     * @param included
     *         {@code true} to include the {@code issuer_state} property.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferCreateRequest setIssuerStateIncluded(boolean included)
    {
        this.issuerStateIncluded = included;

        return this;
    }


    /**
     * Get the flag to include the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object
     * in the {@code grants} object.
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
     * <p>
     * When this property is {@code true}, the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object is
     * included. In addition, Authlete generates a pre-authorized code and puts
     * it in the {@code urn:ietf:params:oauth:grant-type:pre-authorized_code}
     * object as the value of the {@code pre-authorized_code} property. Note
     * that the specification requires that the {@code pre-authorized_code}
     * property exist whenever the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object is
     * included.
     * </p>
     *
     * @return
     *         {@code true} if the
     *         {@code urn:ietf:params:oauth:grant-type:pre-authorized_code}
     *         object will be included.
     */
    public boolean isPreAuthorizedCodeGrantIncluded()
    {
        return preAuthorizedCodeGrantIncluded;
    }


    /**
     * Set the flag to include the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object
     * in the {@code grants} object.
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
     * <p>
     * When this property is {@code true}, the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object is
     * included. In addition, Authlete generates a pre-authorized code and puts
     * it in the {@code urn:ietf:params:oauth:grant-type:pre-authorized_code}
     * object as the value of the {@code pre-authorized_code} property. Note
     * that the specification requires that the {@code pre-authorized_code}
     * property exist whenever the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object is
     * included.
     * </p>
     *
     * @param included
     *         {@code true} to include the
     *         {@code urn:ietf:params:oauth:grant-type:pre-authorized_code}
     *         object.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferCreateRequest setPreAuthorizedCodeGrantIncluded(boolean included)
    {
        this.preAuthorizedCodeGrantIncluded = included;

        return this;
    }


    /**
     * Get the subject associated with the credential offer.
     *
     * <p>
     * This property is mandatory.
     * </p>
     *
     * @return
     *         The subject associated with the credential offer.
     */
    public String getSubject()
    {
        return subject;
    }


    /**
     * Set the subject associated with the credential offer.
     *
     * <p>
     * This property is mandatory.
     * </p>
     *
     * @param subject
     *         The subject associated with the credential offer.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferCreateRequest setSubject(String subject)
    {
        this.subject = subject;

        return this;
    }


    /**
     * Get the duration of the credential offer in seconds.
     *
     * <p>
     * If this property holds a positive integer, the value is used as the
     * duration of the credential offer being issued. Otherwise, the value
     * of the {@code credentialOfferDuration} property of the service is used.
     * </p>
     *
     * @return
     *         The duration of the credential offer in seconds.
     *
     * @see Service#getCredentialOfferDuration()
     */
    public long getDuration()
    {
        return duration;
    }


    /**
     * Set the duration of the credential offer in seconds.
     *
     * <p>
     * If this property holds a positive integer, the value is used as the
     * duration of the credential offer being issued. Otherwise, the value
     * of the {@code credentialOfferDuration} property of the service is used.
     * </p>
     *
     * @param duration
     *         The duration of the credential offer in seconds.
     *
     * @return
     *         {@code this} object.
     *
     * @see Service#setCredentialOfferDuration(long)
     */
    public CredentialOfferCreateRequest setDuration(long duration)
    {
        this.duration = duration;

        return this;
    }


    /**
     * Get the general-purpose arbitrary string associated with the credential
     * offer.
     *
     * <p>
     * Developers can utilize this property as they like. Authlete does not
     * care about the content.
     * </p>
     *
     * @return
     *         The general-purpose arbitrary string.
     */
    public String getContext()
    {
        return context;
    }


    /**
     * Set the general-purpose arbitrary string associated with the credential
     * offer.
     *
     * <p>
     * Developers can utilize this property as they like. Authlete does not
     * care about the content.
     * </p>
     *
     * @param context
     *         The general-purpose arbitrary string.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferCreateRequest setContext(String context)
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
    public CredentialOfferCreateRequest setProperties(Property[] properties)
    {
        this.properties = properties;

        return this;
    }


    /**
     * Get the additional claims in JSON object format that are added to the
     * payload part of the JWT access token.
     *
     * <p>
     * This request parameter has a meaning only when the format of access
     * tokens issued by the service is JWT. In other words, it has a meaning
     * only when the {@code accessTokenSignAlg} property of the {@link Service}
     * holds a non-null value. See the description of the {@link
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
     * This request parameter has a meaning only when the format of access
     * tokens issued by the service is JWT. In other words, it has a meaning
     * only when the {@code accessTokenSignAlg} property of the {@link Service}
     * holds a non-null value. See the description of the {@link
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
    public CredentialOfferCreateRequest setJwtAtClaims(String claims)
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
     *         Must not be negative.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.62
     */
    public CredentialOfferCreateRequest setAuthTime(long authTime)
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
    public CredentialOfferCreateRequest setAcr(String acr)
    {
        this.acr = acr;

        return this;
    }


    /**
     * Get the transaction code that should be associated with the credential
     * offer.
     *
     * <p>
     * If this parameter is not empty and the
     * {@code preAuthorizedCodeGrantIncluded} parameter is true, the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object
     * will include the {@code tx_code} object.
     * </p>
     *
     * <p>
     * The length of the value of this parameter will be used as the value of
     * the {@code length} property in the {@code tx_code} object.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credential_configurations": [ ... ],
     *   "grants": {
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       <span style="color:darkred;">"tx_code"</span>: {
     *         "length": length
     *       }
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
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
     * Set the transaction code that should be associated with the credential
     * offer.
     *
     * <p>
     * If this parameter is not empty and the
     * {@code preAuthorizedCodeGrantIncluded} parameter is true, the
     * {@code urn:ietf:params:oauth:grant-type:pre-authorized_code} object
     * will include the {@code tx_code} object.
     * </p>
     *
     * <p>
     * The length of the value of this parameter will be used as the value of
     * the {@code length} property in the {@code tx_code} object.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credential_configurations": [ ... ],
     *   "grants": {
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       <span style="color:darkred;">"tx_code"</span>: {
     *         "length": length
     *       }
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @param txCode
     *         The transaction code.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.91
     */
    public CredentialOfferCreateRequest setTxCode(String txCode)
    {
        this.txCode = txCode;

        return this;
    }


    /**
     * Get the input mode of the transaction code.
     *
     * <p>
     * The value of this property will be used as the value of the
     * {@code input_mode} property in the {@code tx_code} object.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credential_configurations": [ ... ],
     *   "grants": {
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       "tx_code": {
     *         "length": length,
     *         <span style="color:darkred;">"input_mode": "..."</span>
     *       }
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
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
     * <p>
     * The value of this property will be used as the value of the
     * {@code input_mode} property in the {@code tx_code} object.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credential_configurations": [ ... ],
     *   "grants": {
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       "tx_code": {
     *         "length": length,
     *         <span style="color:darkred;">"input_mode": "..."</span>
     *       }
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * <p>
     * Possible values listed in the current draft of the OID4VCI specification
     * are "{@code numeric}" and "{@code text}" only, but the
     * {@code /vci/offer/create} API accepts other values for the future
     * extension in addition to the predefined ones.
     * </p>
     *
     * @param inputMode
     *         The input mode of the transaction code.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.91
     */
    public CredentialOfferCreateRequest setTxCodeInputMode(String inputMode)
    {
        this.txCodeInputMode = inputMode;

        return this;
    }


    /**
     * Get the description of the transaction code.
     *
     * <p>
     * The value of this property will be used as the value of the
     * {@code description} property in the {@code tx_code} object.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credential_configurations": [ ... ],
     *   "grants": {
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       "tx_code": {
     *         "length": length,
     *         <span style="color:darkred;">"description": "..."</span>
     *       }
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
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
     * <p>
     * The value of this property will be used as the value of the
     * {@code description} property in the {@code tx_code} object.
     * </p>
     *
     * <blockquote>
     * <pre>
     * {
     *   "credential_issuer": "...",
     *   "credential_configurations": [ ... ],
     *   "grants": {
     *     "urn:ietf:params:oauth:grant-type:pre-authorized_code": {
     *       "pre-authorized_code": "...",
     *       "tx_code": {
     *         "length": length,
     *         <span style="color:darkred;">"description": "..."</span>
     *       }
     *     }
     *   }
     * }
     * </pre>
     * </blockquote>
     *
     * @param description
     *         The description of the transaction code. The length of the
     *         description must not exceed 300.
     *
     * @return
     *         {@code this} object.
     *
     * @since 3.91
     */
    public CredentialOfferCreateRequest setTxCodeDescription(String description)
    {
        this.txCodeDescription = description;

        return this;
    }
}
