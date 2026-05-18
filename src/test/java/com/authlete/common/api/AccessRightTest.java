/*
 * Copyright (C) 2026 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.api;


import static com.authlete.common.api.AccessRight.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class AccessRightTest
{
    private static final long SERVICE_ID = 1L;
    private static final long CLIENT_ID = 2L;
    private static final long NONE = 0L;


    // --- canDo: identity ---

    @Test
    public void testCanDoSelf()
    {
        assertTrue(USE_INTROSPECTION.canDo(NONE, SERVICE_ID, USE_INTROSPECTION));
        assertTrue(USE_SERVICE.canDo(NONE, SERVICE_ID, USE_SERVICE));
        assertTrue(MODIFY_SERVICE.canDo(NONE, SERVICE_ID, MODIFY_SERVICE));
        assertTrue(CREATE_CLIENT.canDo(NONE, SERVICE_ID, CREATE_CLIENT));
        assertTrue(ADMIN.canDo(NONE, NONE, ADMIN));
    }


    // --- canDo: USE_INTROSPECTION subsumption ---

    @Test
    public void testUseServiceIncludesUseIntrospection()
    {
        assertTrue(USE_SERVICE.canDo(NONE, SERVICE_ID, USE_INTROSPECTION));
    }

    @Test
    public void testModifyServiceIncludesUseIntrospection()
    {
        assertTrue(MODIFY_SERVICE.canDo(NONE, SERVICE_ID, USE_INTROSPECTION));
    }

    @Test
    public void testCreateClientIncludesUseIntrospection()
    {
        assertTrue(CREATE_CLIENT.canDo(NONE, SERVICE_ID, USE_INTROSPECTION));
    }

    @Test
    public void testAdminIncludesUseIntrospection()
    {
        assertTrue(ADMIN.canDo(NONE, SERVICE_ID, USE_INTROSPECTION));
    }


    // --- canDo: VIEW_SERVICE subsumption ---

    @Test
    public void testUseServiceIncludesViewService()
    {
        assertTrue(USE_SERVICE.canDo(NONE, SERVICE_ID, VIEW_SERVICE));
    }

    @Test
    public void testModifyServiceIncludesViewService()
    {
        assertTrue(MODIFY_SERVICE.canDo(NONE, SERVICE_ID, VIEW_SERVICE));
    }

    @Test
    public void testCreateClientIncludesViewService()
    {
        assertTrue(CREATE_CLIENT.canDo(NONE, SERVICE_ID, VIEW_SERVICE));
    }


    // --- canDo: missing service/client IDs ---

    @Test
    public void testServiceSpecificRequiresServiceId()
    {
        assertFalse(USE_INTROSPECTION.canDo(NONE, NONE, USE_INTROSPECTION));
        assertFalse(USE_SERVICE.canDo(NONE, NONE, USE_SERVICE));
        assertFalse(MODIFY_SERVICE.canDo(NONE, NONE, MODIFY_SERVICE));
    }

    @Test
    public void testClientSpecificRequiresBoth()
    {
        assertFalse(VIEW_CLIENT.canDo(NONE, SERVICE_ID, VIEW_CLIENT));
        assertFalse(VIEW_CLIENT.canDo(CLIENT_ID, NONE, VIEW_CLIENT));
        assertTrue(VIEW_CLIENT.canDo(CLIENT_ID, SERVICE_ID, VIEW_CLIENT));
    }


    // --- canDo: no upward escalation ---

    @Test
    public void testUseIntrospectionDoesNotIncludeUseService()
    {
        assertFalse(USE_INTROSPECTION.canDo(NONE, SERVICE_ID, USE_SERVICE));
    }

    @Test
    public void testViewServiceDoesNotIncludeModifyService()
    {
        assertFalse(VIEW_SERVICE.canDo(NONE, SERVICE_ID, MODIFY_SERVICE));
    }

    @Test
    public void testUseServiceDoesNotIncludeModifyService()
    {
        assertFalse(USE_SERVICE.canDo(NONE, SERVICE_ID, MODIFY_SERVICE));
    }


    // --- canDoAny: USE_INTROSPECTION ---

    @Test
    public void testCanDoAnyUseServiceIncludesUseIntrospection()
    {
        assertTrue(USE_SERVICE.canDoAny(SERVICE_ID, USE_INTROSPECTION));
    }

    @Test
    public void testCanDoAnyModifyServiceIncludesUseIntrospection()
    {
        assertTrue(MODIFY_SERVICE.canDoAny(SERVICE_ID, USE_INTROSPECTION));
    }

    @Test
    public void testCanDoAnyCreateClientIncludesUseIntrospection()
    {
        assertTrue(CREATE_CLIENT.canDoAny(SERVICE_ID, USE_INTROSPECTION));
    }

    @Test
    public void testCanDoAnyAdminIncludesUseIntrospection()
    {
        assertTrue(ADMIN.canDoAny(SERVICE_ID, USE_INTROSPECTION));
    }

    @Test
    public void testCanDoAnyClientSpecificRequiresServiceId()
    {
        // VIEW_CLIENT is clientSpecific — canDoAny requires a serviceId in that case
        assertFalse(VIEW_CLIENT.canDoAny(NONE, VIEW_CLIENT));
        assertTrue(VIEW_CLIENT.canDoAny(SERVICE_ID, VIEW_CLIENT));
    }
}
