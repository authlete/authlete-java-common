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


import java.util.List;
import java.util.Map;


/**
 * The class that represents the constraint for elements in
 * {@code verified_claims/verification/evidence}.
 *
 * @see <a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
 *      >OpenID Connect for Identity Assurance 1.0</a>
 *
 * @since 2.63
 */
public class EvidenceConstraint extends BaseConstraint
{
    private static final String[] ID_DOCUMENT_TOP_LEVEL_PROPERTIES =
            new String[] { "method", "verifier", "time", "document" };

    private static final String[] QES_TOP_LEVEL_PROPERTIES =
            new String[] { "issuer", "serial_number", "created_at" };

    private static final String[] UTILITY_BILL_TOP_LEVEL_PROPERTIES =
            new String[] { "provider", "date" };


    private LeafConstraint type;


    /**
     * Get the constraint for {@code type}.
     *
     * @return
     *         The constraint for {@code type}.
     */
    public LeafConstraint getType()
    {
        return type;
    }


    /**
     * Set the constraint for {@code type}.
     *
     * @param constraint
     *         The constraint for {@code type}.
     */
    public void setType(LeafConstraint constraint)
    {
        this.type = constraint;
    }


    /**
     * Create an instance of a subclass of {@code EvidenceConstraint} from
     * an object in the given list.
     *
     * @param list
     *         A list that represents an {@code "evidence"} array.
     *
     * @param index
     *         The position in the list.
     *
     * @param key
     *         The key that identifies the array. In normal cases, the key is
     *         {@code "evidence"}.
     *
     * @return
     *         An instance of a subclass of {@code EvidenceConstraint} that
     *         represents the element in the {@code "evidence"} array.
     *
     * @throws ConstraintException
     *         The structure of the object does not conform to the specification
     *         (<a href="https://openid.net/specs/openid-connect-4-identity-assurance-1_0.html"
     *         >OpenID Connect for Identity Assurance 1.0</a>).
     */
    public static EvidenceConstraint extract(List<?> list, int index, String key) throws ConstraintException
    {
        String label = String.format("%s[%d]", key, index);

        Object object = list.get(index);

        Helper.ensureNotNull(object, label);

        Map<?,?> map = Helper.ensureMap(object, label);

        Class<? extends EvidenceConstraint> klass = guess(map, key, index);

        if (klass == IDDocumentConstraint.class)
        {
            return IDDocumentConstraint.extract(map);
        }

        if (klass == QESConstraint.class)
        {
            return QESConstraint.extract(map);
        }

        if (klass == UtilityBillConstraint.class)
        {
            return UtilityBillConstraint.extract(map);
        }

        // This won't happen.
        return null;
    }


    private static Class<? extends EvidenceConstraint> guess(Map<?,?> map, String key, int index)
    {
        Class<? extends EvidenceConstraint> klass = guessByType(map, key, index);

        if (klass != null)
        {
            return klass;
        }

        if (containsAny(map, ID_DOCUMENT_TOP_LEVEL_PROPERTIES))
        {
            return IDDocumentConstraint.class;
        }

        if (containsAny(map, QES_TOP_LEVEL_PROPERTIES))
        {
            return QESConstraint.class;
        }

        if (containsAny(map, UTILITY_BILL_TOP_LEVEL_PROPERTIES))
        {
            return UtilityBillConstraint.class;
        }

        throw Helper.exception("'%s[%d]' is not a known evidence.", key, index);
    }


    private static Class<? extends EvidenceConstraint> guessByType(Map<?,?> map, String key, int index)
    {
        LeafConstraint type = LeafConstraint.extract(map, "type");

        if (!type.exists())
        {
            return null;
        }

        String value = type.getValue();

        if (value == null)
        {
            // "type":null ---> This means just that "type" is required.
            return null;
        }

        if (value.equals("id_document"))
        {
            return IDDocumentConstraint.class;
        }

        if (value.equals("qes"))
        {
            return QESConstraint.class;
        }

        if (value.equals("utility_bill"))
        {
            return UtilityBillConstraint.class;
        }

        throw Helper.exception("The type of '%s[%d]' is unknown.", key, index);
    }


    private static boolean containsAny(Map<?,?> map, String[] names)
    {
        for (String name : names)
        {
            if (map.containsKey(name))
            {
                return true;
            }
        }

        return false;
    }


    static void fill(EvidenceConstraint instance, Map<?,?> map)
    {
        instance.type = LeafConstraint.extract(map, "type");
    }


    @Override
    public Map<String, Object> toMap()
    {
        Map<String, Object> map = super.toMap();

        if (map == null)
        {
            return null;
        }

        addIfAvailable(map, "type", type);

        return map;
    }
}
