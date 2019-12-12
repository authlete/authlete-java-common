/*
 * Copyright (C) 2019 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package com.authlete.common.assurance.constraint;


import static org.junit.Assert.*;
import org.junit.Test;
import com.authlete.common.assurance.constraint.ClaimsConstraint;
import com.authlete.common.assurance.constraint.EvidenceArrayConstraint;
import com.authlete.common.assurance.constraint.EvidenceConstraint;
import com.authlete.common.assurance.constraint.IDDocumentConstraint;
import com.authlete.common.assurance.constraint.LeafConstraint;
import com.authlete.common.assurance.constraint.QESConstraint;
import com.authlete.common.assurance.constraint.TimeConstraint;
import com.authlete.common.assurance.constraint.UtilityBillConstraint;
import com.authlete.common.assurance.constraint.VerificationConstraint;
import com.authlete.common.assurance.constraint.VerifiedClaimsConstraint;
import com.authlete.common.assurance.constraint.VerifiedClaimsContainerConstraint;


public class VerifiedClaimsContainerConstraintTest
{
    private static VerifiedClaimsContainerConstraint toContainer(String json)
    {
        return VerifiedClaimsContainerConstraint.fromJson(json);
    }


    private static VerifiedClaimsConstraint toVerifiedClaims(String json)
    {
        return toContainer(json).getVerifiedClaims();
    }


    private static VerificationConstraint toVerification(String json)
    {
        return toVerifiedClaims(json).getVerification();
    }


    private static ClaimsConstraint toClaims(String json)
    {
        return toVerifiedClaims(json).getClaims();
    }


    @Test
    public void testEmpty()
    {
        String json = String.join("\n",
                "{",
                "}");

        VerifiedClaimsContainerConstraint container = toContainer(json);

        assertTrue( container.exists());
        assertFalse(container.isNull());

        assertFalse(container.getVerifiedClaims().exists());
    }


    @Test
    public void testVerifiedClaimsNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":null",
                "}");

        VerifiedClaimsConstraint verifiedClaims = toVerifiedClaims(json);

        assertTrue(verifiedClaims.exists());
        assertTrue(verifiedClaims.isNull());
    }


    @Test
    public void testVerifiedClaimsEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "  }",
                "}");

        VerifiedClaimsConstraint verifiedClaims = toVerifiedClaims(json);

        assertTrue (verifiedClaims.exists());
        assertFalse(verifiedClaims.isNull());

        assertFalse(verifiedClaims.getVerification().exists());
        assertFalse(verifiedClaims.getClaims().exists());
    }


    @Test
    public void testVerificationNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":null",
                "  }",
                "}");

        VerificationConstraint verification = toVerification(json);

        assertTrue(verification.exists());
        assertTrue(verification.isNull());
    }


    @Test
    public void testVerificationEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "    }",
                "  }",
                "}");

        VerificationConstraint verification = toVerification(json);

        assertTrue( verification.exists());
        assertFalse(verification.isNull());

        assertFalse(verification.getTrustFramework().exists());
        assertFalse(verification.getTime().exists());
        assertFalse(verification.getVerificationProcess().exists());
        assertFalse(verification.getEvidence().exists());
    }


    @Test
    public void testClaimsNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"claims\":null",
                "  }",
                "}");

        ClaimsConstraint claims = toClaims(json);

        assertTrue(claims.exists());
        assertTrue(claims.isNull());
    }


    @Test
    public void testClaimsEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"claims\":{",
                "    }",
                "  }",
                "}");

        ClaimsConstraint claims = toClaims(json);

        assertTrue( claims.exists());
        assertFalse(claims.isNull());
    }


    @Test
    public void testVerificationElementsNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":null,",
                "      \"time\":null,",
                "      \"verification_process\":null,",
                "      \"evidence\":null",
                "    }",
                "  }",
                "}");

        VerificationConstraint verification = toVerification(json);

        assertTrue(verification.getTrustFramework().exists());
        assertTrue(verification.getTrustFramework().isNull());

        assertTrue(verification.getTime().exists());
        assertTrue(verification.getTime().isNull());

        assertTrue(verification.getVerificationProcess().exists());
        assertTrue(verification.getVerificationProcess().isNull());

        assertTrue(verification.getEvidence().exists());
        assertTrue(verification.getEvidence().isNull());
    }


    @Test
    public void testVerificationElementsEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":{",
                "      },",
                "      \"time\":{",
                "      },",
                "      \"verification_process\":{",
                "      },",
                "      \"evidence\":[",
                "      ]",
                "    }",
                "  }",
                "}");

        VerificationConstraint verification = toVerification(json);

        assertTrue( verification.getTrustFramework().exists());
        assertFalse(verification.getTrustFramework().isNull());

        assertTrue( verification.getTime().exists());
        assertFalse(verification.getTime().isNull());

        assertTrue( verification.getVerificationProcess().exists());
        assertFalse(verification.getVerificationProcess().isNull());

        assertTrue( verification.getEvidence().exists());
        assertFalse(verification.getEvidence().isNull());
    }


    @Test
    public void testTrustFramework()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":{",
                "        \"essential\":true,",
                "        \"value\":\"de_aml\",",
                "        \"values\":[\"eidas_ial_substantial\",\"eidas_ial_high\"]",
                "      }",
                "    }",
                "  }",
                "}");

        LeafConstraint trustFramework = toVerification(json).getTrustFramework();

        assertTrue(trustFramework.isEssential());
        assertEquals("de_aml", trustFramework.getValue());
        assertArrayEquals(new String[]{"eidas_ial_substantial","eidas_ial_high"}, trustFramework.getValues());
    }


    @Test
    public void testVerificationTime()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"time\":{",
                "        \"essential\":true,",
                "        \"value\":\"time\",",
                "        \"values\":[\"time0\",\"time1\"],",
                "        \"max_age\":12345",
                "      }",
                "    }",
                "  }",
                "}");

        TimeConstraint time = toVerification(json).getTime();

        assertTrue(time.isEssential());
        assertEquals("time", time.getValue());
        assertArrayEquals(new String[]{"time0","time1"}, time.getValues());
        assertEquals(12345, time.getMaxAge());
    }


    @Test
    public void testVerificationProcess()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"verification_process\":{",
                "        \"essential\":true,",
                "        \"value\":\"process\",",
                "        \"values\":[\"process0\",\"process1\"]",
                "      }",
                "    }",
                "  }",
                "}");

        LeafConstraint process = toVerification(json).getVerificationProcess();

        assertTrue(process.isEssential());
        assertEquals("process", process.getValue());
        assertArrayEquals(new String[]{"process0","process1"}, process.getValues());
    }


    @Test
    public void testIDDocument()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"type\":{",
                "            \"value\":\"id_document\"",
                "          },",
                "          \"method\":{",
                "            \"value\":\"pipp\",",
                "            \"values\":[\"sripp\",\"eid\",\"uripp\"]",
                "          },",
                "          \"verifier\":{",
                "            \"organization\":{",
                "              \"value\":\"org\"",
                "            },",
                "            \"txn\":{",
                "              \"value\":\"txn\"",
                "            }",
                "          },",
                "          \"time\":{",
                "            \"value\":\"time\",",
                "            \"max_age\":12345",
                "          }",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        EvidenceArrayConstraint array = toVerification(json).getEvidence();

        assertTrue(array.exists());
        assertEquals(1, array.size());

        EvidenceConstraint evidence = array.get(0);

        assertTrue(evidence instanceof IDDocumentConstraint);

        IDDocumentConstraint idDocument = (IDDocumentConstraint)evidence;

        assertTrue( idDocument.exists());
        assertFalse(idDocument.isNull());

        // id_document/type
        assertTrue(idDocument.getType().exists());
        assertEquals("id_document", idDocument.getType().getValue());

        // id_document/method
        assertTrue(idDocument.getMethod().exists());
        assertEquals("pipp", idDocument.getMethod().getValue());
        assertArrayEquals(new String[]{"sripp","eid","uripp"}, idDocument.getMethod().getValues());

        // id_document/verifier
        assertTrue(idDocument.getVerifier().exists());

        // id_document/verifier/organization
        assertTrue(idDocument.getVerifier().getOrganization().exists());
        assertEquals("org", idDocument.getVerifier().getOrganization().getValue());

        // id_document/verifier/txn
        assertTrue(idDocument.getVerifier().getTxn().exists());
        assertEquals("txn", idDocument.getVerifier().getTxn().getValue());

        // id_document/time
        assertTrue(idDocument.getTime().exists());
        assertEquals("time", idDocument.getTime().getValue());
        assertEquals(12345, idDocument.getTime().getMaxAge());
    }


    @Test
    public void testQES()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"type\":{",
                "            \"value\":\"qes\"",
                "          },",
                "          \"issuer\":{",
                "            \"value\":\"issuer\"",
                "          },",
                "          \"serial_number\":{",
                "            \"value\":\"12345\"",
                "          },",
                "          \"created_at\":null",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        EvidenceArrayConstraint array = toVerification(json).getEvidence();

        assertTrue(array.exists());
        assertEquals(1, array.size());

        EvidenceConstraint evidence = array.get(0);

        assertTrue(evidence instanceof QESConstraint);

        QESConstraint qes = (QESConstraint)evidence;

        assertTrue( qes.exists());
        assertFalse(qes.isNull());

        // qes/type
        assertTrue(qes.getType().exists());
        assertEquals("qes", qes.getType().getValue());

        // qes/issuer
        assertTrue(qes.getIssuer().exists());
        assertEquals("issuer", qes.getIssuer().getValue());

        // qes/serial_number
        assertTrue(qes.getSerialNumber().exists());
        assertEquals("12345", qes.getSerialNumber().getValue());

        // qes/created_at
        assertTrue(qes.getCreatedAt().exists());
        assertNull(qes.getCreatedAt().getValue());
    }


    @Test
    public void testUtilityBill()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"type\":{",
                "            \"value\":\"utility_bill\"",
                "          },",
                "          \"provider\":{",
                "            \"name\":{",
                "              \"value\":\"name\",",
                "              \"values\":[\"name0\",\"name1\"]",
                "            },",
                "            \"country\":{",
                "              \"value\":\"country\"",
                "            },",
                "            \"region\":{",
                "              \"essential\":true",
                "            },",
                "            \"street_address\":null",
                "          },",
                "          \"date\":null",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        EvidenceArrayConstraint array = toVerification(json).getEvidence();

        assertTrue(array.exists());
        assertEquals(1, array.size());

        EvidenceConstraint evidence = array.get(0);

        assertTrue(evidence instanceof UtilityBillConstraint);

        UtilityBillConstraint utilityBill = (UtilityBillConstraint)evidence;

        assertTrue( utilityBill.exists());
        assertFalse(utilityBill.isNull());

        // utility_bill/type
        assertTrue(utilityBill.getType().exists());
        assertEquals("utility_bill", utilityBill.getType().getValue());

        // utility_bill/provider
        assertTrue(utilityBill.getProvider().exists());

        // utility_bill/provider/name
        assertTrue(utilityBill.getProvider().getName().exists());
        assertEquals("name", utilityBill.getProvider().getName().getValue());
        assertArrayEquals(new String[]{"name0","name1"}, utilityBill.getProvider().getName().getValues());

        // utility_bill/provider/country
        assertTrue(utilityBill.getProvider().getCountry().exists());
        assertEquals("country", utilityBill.getProvider().getCountry().getValue());

        // utility_bill/provider/region
        assertTrue(utilityBill.getProvider().getRegion().exists());
        assertTrue(utilityBill.getProvider().getRegion().isEssential());

        // utility_bill/provider/street_address
        assertTrue(utilityBill.getProvider().getStreetAddress().exists());

        // utility_bill/date
        assertTrue(utilityBill.getDate().exists());
    }


    @Test
    public void testIDDocumentByMethod()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"method\":null",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        EvidenceConstraint constraint = toVerification(json).getEvidence().get(0);

        assertTrue(constraint instanceof IDDocumentConstraint);
    }


    @Test
    public void testIDDocumentByVerifier()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"verifier\":null",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        EvidenceConstraint constraint = toVerification(json).getEvidence().get(0);

        assertTrue(constraint instanceof IDDocumentConstraint);
    }


    @Test
    public void testIDDocumentByDocument()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"document\":null",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        EvidenceConstraint constraint = toVerification(json).getEvidence().get(0);

        assertTrue(constraint instanceof IDDocumentConstraint);
    }


    @Test
    public void testQESByIssuer()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"issuer\":null",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        EvidenceConstraint constraint = toVerification(json).getEvidence().get(0);

        assertTrue(constraint instanceof QESConstraint);
    }


    @Test
    public void testUtilityBillByProvider()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"provider\":null",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        EvidenceConstraint constraint = toVerification(json).getEvidence().get(0);

        assertTrue(constraint instanceof UtilityBillConstraint);
    }


    @Test(expected = ConstraintException.class)
    public void testEvidenceElementNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        null",
                "      ]",
                "    }",
                "  }",
                "}");

        toContainer(json);
    }


    @Test(expected = ConstraintException.class)
    public void testEvidenceElementEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        toContainer(json);
    }


    @Test(expected = ConstraintException.class)
    public void testEvidenceElementTypeNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"type\":null",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        toContainer(json);
    }


    @Test(expected = ConstraintException.class)
    public void testEvidenceElementTypeValueNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"type\":{",
                "            \"value\":null",
                "          }",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        toContainer(json);
    }


    @Test(expected = ConstraintException.class)
    public void testEvidenceElementTypeValueUnknown()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"evidence\":[",
                "        {",
                "          \"type\":{",
                "            \"value\":\"unknown\"",
                "          }",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        toContainer(json);
    }


    @Test
    public void testClaimNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"claims\":{",
                "      \"title\":null",
                "    }",
                "  }",
                "}");

        ClaimsConstraint claims = toClaims(json);

        assertTrue(claims.exists());
        assertEquals(1, claims.size());

        VerifiedClaimConstraint claim = claims.get("title");
        assertNotNull(claim);

        assertTrue(claim.exists());
        assertTrue(claim.isNull());
    }


    @Test
    public void testClaimEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"claims\":{",
                "      \"title\":{",
                "      }",
                "    }",
                "  }",
                "}");

        ClaimsConstraint claims = toClaims(json);

        assertTrue(claims.exists());
        assertEquals(1, claims.size());

        VerifiedClaimConstraint claim = claims.get("title");
        assertNotNull(claim);

        assertTrue( claim.exists());
        assertFalse(claim.isNull());
    }


    @Test
    public void testClaim()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"claims\":{",
                "      \"title\":{",
                "        \"essential\":true,",
                "        \"value\":\"title\",",
                "        \"values\":[\"title0\",\"title1\"],",
                "        \"purpose\":\"purpose\"",
                "      }",
                "    }",
                "  }",
                "}");

        ClaimsConstraint claims = toClaims(json);

        assertTrue(claims.exists());
        assertEquals(1, claims.size());

        VerifiedClaimConstraint claim = claims.get("title");
        assertNotNull(claim);

        assertTrue( claim.exists());
        assertFalse(claim.isNull());

        assertTrue(claim.isEssential());
        assertEquals("title", claim.getValue());
        assertArrayEquals(new String[]{"title0","title1"}, claim.getValues());
        assertEquals("purpose", claim.getPurpose());
    }
}
