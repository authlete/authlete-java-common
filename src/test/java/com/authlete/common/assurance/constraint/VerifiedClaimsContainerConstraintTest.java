/*
 * Copyright (C) 2019-2020 Authlete, Inc.
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
import java.io.IOException;

import com.authlete.common.util.digest.Digest;
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


    private static void compareJson(String expectedJson, BaseConstraint constraint)
    {
        String actualJson = constraint.toJson(true);

        String expectedDigest = digestJson(expectedJson);
        String actualDigest   = digestJson(actualJson);

        if (!actualDigest.equals(expectedDigest))
        {
            System.out.format("Expected JSON:\n%s\n", expectedJson);
            System.out.format("Actual JSON:\n%s\n", actualJson);
        }

        assertEquals(expectedDigest, actualDigest);
    }


    private static String digestJson(String json)
    {
        try
        {
            return Digest.getInstanceSHA256().updateJson(json).digestAsString();
        }
        catch (IOException e)
        {
            return null;
        }
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

        compareJson(json, container);
    }


    @Test
    public void testVerifiedClaimsNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":null",
                "}");

        VerifiedClaimsContainerConstraint container = toContainer(json);
        VerifiedClaimsConstraint verifiedClaims = container.getVerifiedClaims();

        assertTrue(verifiedClaims.exists());
        assertTrue(verifiedClaims.isNull());

        compareJson(json, container);
    }


    @Test
    public void testVerifiedClaimsEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "  }",
                "}");

        VerifiedClaimsContainerConstraint container = toContainer(json);
        VerifiedClaimsConstraint verifiedClaims = container.getVerifiedClaims();

        assertTrue (verifiedClaims.exists());
        assertFalse(verifiedClaims.isNull());

        assertFalse(verifiedClaims.getVerification().exists());
        assertFalse(verifiedClaims.getClaims().exists());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        VerificationConstraint verification =
                container.getVerifiedClaims().getVerification();

        assertTrue(verification.exists());
        assertTrue(verification.isNull());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        VerificationConstraint verification =
                container.getVerifiedClaims().getVerification();

        assertTrue( verification.exists());
        assertFalse(verification.isNull());

        assertFalse(verification.getTrustFramework().exists());
        assertFalse(verification.getTime().exists());
        assertFalse(verification.getVerificationProcess().exists());
        assertFalse(verification.getEvidence().exists());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        ClaimsConstraint claims = container.getVerifiedClaims().getClaims();

        assertTrue(claims.exists());
        assertTrue(claims.isNull());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        ClaimsConstraint claims = container.getVerifiedClaims().getClaims();

        assertTrue( claims.exists());
        assertFalse(claims.isNull());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        VerificationConstraint verification =
                container.getVerifiedClaims().getVerification();

        assertTrue(verification.getTrustFramework().exists());
        assertTrue(verification.getTrustFramework().isNull());

        assertTrue(verification.getTime().exists());
        assertTrue(verification.getTime().isNull());

        assertTrue(verification.getVerificationProcess().exists());
        assertTrue(verification.getVerificationProcess().isNull());

        assertTrue(verification.getEvidence().exists());
        assertTrue(verification.getEvidence().isNull());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        VerificationConstraint verification =
                container.getVerifiedClaims().getVerification();

        assertTrue( verification.getTrustFramework().exists());
        assertFalse(verification.getTrustFramework().isNull());

        assertTrue( verification.getTime().exists());
        assertFalse(verification.getTime().isNull());

        assertTrue( verification.getVerificationProcess().exists());
        assertFalse(verification.getVerificationProcess().isNull());

        assertTrue( verification.getEvidence().exists());
        assertFalse(verification.getEvidence().isNull());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        LeafConstraint trustFramework =
                container.getVerifiedClaims().getVerification().getTrustFramework();

        assertTrue(trustFramework.isEssential());
        assertEquals("de_aml", trustFramework.getValue());
        assertArrayEquals(new String[]{"eidas_ial_substantial","eidas_ial_high"}, trustFramework.getValues());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        TimeConstraint time =
                container.getVerifiedClaims().getVerification().getTime();

        assertTrue(time.isEssential());
        assertEquals("time", time.getValue());
        assertArrayEquals(new String[]{"time0","time1"}, time.getValues());
        assertEquals(12345, time.getMaxAge());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        LeafConstraint process =
                container.getVerifiedClaims().getVerification().getVerificationProcess();

        assertTrue(process.isEssential());
        assertEquals("process", process.getValue());
        assertArrayEquals(new String[]{"process0","process1"}, process.getValues());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        EvidenceArrayConstraint array =
                container.getVerifiedClaims().getVerification().getEvidence();

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

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        EvidenceArrayConstraint array =
                container.getVerifiedClaims().getVerification().getEvidence();

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

        compareJson(json, container);
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
                "            \"formatted\":null,",
                "            \"street_address\":null,",
                "            \"locality\":null,",
                "            \"region\":{",
                "              \"essential\":true",
                "            },",
                "            \"postal_code\":{",
                "              \"value\":\"12345\"",
                "            },",
                "            \"country\":{",
                "              \"value\":\"country\"",
                "            }",
                "          },",
                "          \"date\":null",
                "        }",
                "      ]",
                "    }",
                "  }",
                "}");

        VerifiedClaimsContainerConstraint container = toContainer(json);
        EvidenceArrayConstraint array =
                container.getVerifiedClaims().getVerification().getEvidence();

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

        // utility_bill/provider/formatted
        assertTrue(utilityBill.getProvider().getFormatted().exists());

        // utility_bill/provider/street_address
        assertTrue(utilityBill.getProvider().getStreetAddress().exists());

        // utility_bill/provider/locality
        assertTrue(utilityBill.getProvider().getLocality().exists());

        // utility_bill/provider/region
        assertTrue(utilityBill.getProvider().getRegion().exists());
        assertTrue(utilityBill.getProvider().getRegion().isEssential());

        // utility_bill/provider/postal_code
        assertTrue(utilityBill.getProvider().getPostalCode().exists());
        assertEquals("12345", utilityBill.getProvider().getPostalCode().getValue());

        // utility_bill/provider/country
        assertTrue(utilityBill.getProvider().getCountry().exists());
        assertEquals("country", utilityBill.getProvider().getCountry().getValue());

        // utility_bill/date
        assertTrue(utilityBill.getDate().exists());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        EvidenceConstraint constraint =
                container.getVerifiedClaims().getVerification().getEvidence().get(0);

        assertTrue(constraint instanceof IDDocumentConstraint);

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        EvidenceConstraint constraint =
                container.getVerifiedClaims().getVerification().getEvidence().get(0);

        assertTrue(constraint instanceof IDDocumentConstraint);

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        EvidenceConstraint constraint =
                container.getVerifiedClaims().getVerification().getEvidence().get(0);

        assertTrue(constraint instanceof IDDocumentConstraint);

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        EvidenceConstraint constraint =
                container.getVerifiedClaims().getVerification().getEvidence().get(0);

        assertTrue(constraint instanceof QESConstraint);

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        EvidenceConstraint constraint =
                container.getVerifiedClaims().getVerification().getEvidence().get(0);

        assertTrue(constraint instanceof UtilityBillConstraint);

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        ClaimsConstraint claims = container.getVerifiedClaims().getClaims();

        assertTrue(claims.exists());
        assertEquals(1, claims.size());

        VerifiedClaimConstraint claim = claims.get("title");
        assertNotNull(claim);

        assertTrue(claim.exists());
        assertTrue(claim.isNull());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        ClaimsConstraint claims = container.getVerifiedClaims().getClaims();

        assertTrue(claims.exists());
        assertEquals(1, claims.size());

        VerifiedClaimConstraint claim = claims.get("title");
        assertNotNull(claim);

        assertTrue( claim.exists());
        assertFalse(claim.isNull());

        compareJson(json, container);
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

        VerifiedClaimsContainerConstraint container = toContainer(json);
        ClaimsConstraint claims = container.getVerifiedClaims().getClaims();

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

        compareJson(json, container);
    }
}
