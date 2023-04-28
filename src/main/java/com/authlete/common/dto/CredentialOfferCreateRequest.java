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
 * @since 3.59
 * @since Authlete 3.0
 *
 * @see <a href="https://openid.net/specs/openid-4-verifiable-credential-issuance-1_0.html"
 *      >OpenID for Verifiable Credential Issuance</a>
 */
public class CredentialOfferCreateRequest implements Serializable
{
    private static final long serialVersionUID = 2L;


    /**
     * The value of the {@code credentials} object in the JSON format.
     */
    private String credentials;


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
     * The flag to include the {@code user_pin_required} property with the
     * value {@code true}.
     */
    private boolean userPinRequired;


    /**
     * The length of the user PIN to generate.
     */
    private int userPinLength;


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
     * Get the value of the {@code credentials} object in the JSON format.
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
     * This property is mandatory.
     * </p>
     *
     * @return
     *         The value of the {@code credentials} object.
     */
    public String getCredentials()
    {
        return credentials;
    }


    /**
     * Set the value of the {@code credentials} object in the JSON format.
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
     * This property is mandatory.
     * </p>
     *
     * @param credentials
     *         The value of the {@code credentials} object.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferCreateRequest setCredentials(String credentials)
    {
        this.credentials = credentials;

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
     *   "credentials": [ ... ],
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
     *   "credentials": [ ... ],
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
     *   "credentials": [ ... ],
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
     *   "credentials": [ ... ],
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
     * Get the flag to include the {@code user_pin_required} property with the
     * value {@code true}.
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
     * @return
     *         {@code true} if the {@code user_pin_required} property will be
     *         included with the value {@code true}.
     *         {@code false} if the property will be omitted.
     */
    public boolean isUserPinRequired()
    {
        return userPinRequired;
    }


    /**
     * Set the flag to include the {@code user_pin_required} property with the
     * value {@code true}.
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
     * @param included
     *         {@code true} to include the {@code user_pin_required} property
     *         with the value {@code true}. {@code false} to omit the property.
     *
     * @return
     *         {@code this} object.
     */
    public CredentialOfferCreateRequest setUserPinRequired(boolean included)
    {
        this.userPinRequired = included;

        return this;
    }


    /**
     * Get the length of the user PIN associated with the credential offer.
     *
     * <p>
     * Authlete generates a user PIN of the specified length when necessary.
     * The maximum length that can be specified is 8 as the specification
     * requires so. When this property is omitted or its value is 0 or
     * negative, the value of the {@code userPinLength} property of the
     * service is used.
     * </p>
     *
     * @return
     *         The length of the user PIN.
     *
     * @see Service#getUserPinLength()
     */
    public int getUserPinLength()
    {
        return userPinLength;
    }


    /**
     * Set the length of the user PIN associated with the credential offer.
     *
     * <p>
     * Authlete generates a user PIN of the specified length when necessary.
     * The maximum length that can be specified is 8 as the specification
     * requires so. When this property is omitted or its value is 0 or
     * negative, the value of the {@code userPinLength} property of the
     * service is used.
     * </p>
     *
     * @param length
     *         The length of the user PIN.
     *
     * @return
     *         {@code this} object.
     *
     * @see Service#setUserPinLength(int)
     */
    public CredentialOfferCreateRequest setUserPinLength(int length)
    {
        this.userPinLength = length;

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
}
