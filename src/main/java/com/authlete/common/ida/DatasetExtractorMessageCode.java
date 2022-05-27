/*
 * Copyright (C) 2022 Authlete, Inc.
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
package com.authlete.common.ida;


import java.util.ResourceBundle;


/**
 * Identifiers of log messages emitted from {@link DatasetExtractor}.
 *
 * <p>
 * Actual messages are stored in {@code dataset-extractor-messages.properties}.
 * </p>
 *
 * @since 3.17
 */
enum DatasetExtractorMessageCode
{
    /** A transformed claim was found. */
    DE01,

    /** The property is unavailable, and therefore omitted. */
    DE02,

    /** The property is available as array or object, but omitted for the data minimization policy. */
    DE03,

    /** The request does not have constraints for the property, and therefore the property is put in the copy unconditionally. */
    DE04,

    /** The request format is invalid, so matching fails. */
    DE05,

    /** The property does not satisfy the constraint, so matching fails. */
    DE06,

    /** The property does not satisfy the constraint, and therefore the property is omitted. */
    DE07,

    /** The property satisfies the constraint, and therefore the property is put in the copy. */
    DE08,

    /** The request has no constraint for the property, and therefore the property is put in the copy unconditionally. */
    DE09,

    /** The request has no constraint for the property, but the property is omitted because its value is unavailable. */
    DE10,

    /** The request has sub properties but the actual data in the original dataset is a single value, so matching fails. */
    DE11,

    /** The element in the array in the original dataset is not an object. The element is ignored. */
    DE12,

    /** The element in the array in the original dataset meets conditions of the request, so the element is put in the copy. */
    DE13,

    /** None of the elements in the array in the original dataset meet conditions of the request, so matching fails. */
    DE14,

    /** The request is an array, but the property in the original dataset is neither an object nor an array. Therefore, matching fails. */
    DE15,

    /** The element in the array in the request is not an object. It is a specification violation. The element is ignored. */
    DE16,

    /** The property in the original dataset satisfies conditions of the element in the array in the request. */
    DE17,

    /** The element in the array in the original dataset is not an object. The element is ignored. */
    DE18,

    /** The element in the array in the request is not an object. It is a specification violation. The element is ignored. */
    DE19,

    /** The element in the array in the original dataset satisfies conditions of the element in the array in the request. */
    DE20,

    /** None of the elements in the array in the original dataset satisfy any of the elements in the array in the request. Therefore, matching fails. */
    DE21,

    /** Some elements in the array in the original dataset satisfy any of the elements in the array in the request. */
    DE22,

    /** All available sub-elements under 'assurance_details' are unconditionally returned based on the special rule for the property. */
    DE23,
    ;


    /**
     * Holder of a {@link ResourceBundle} instance that corresponds to
     * {@code "dataset-extractor-messages"}.
     */
    private static final class ResourceBundleInstanceHolder
    {
        private static final ResourceBundle INSTANCE =
                ResourceBundle.getBundle("dataset-extractor-messages");
    }


    /**
     * Build a string by using the string resource which corresponds to this
     * enum as a format.
     *
     * @param args
     *         Arguments referenced in the format string.
     *
     * @return
     *         A built string.
     */
    public String format(Object... args)
    {
        // Get the string resource that corresponds to this enum.
        String resource = ResourceBundleInstanceHolder.INSTANCE.getString(name());

        // Build a message by using the string resource as a format.
        String message  = String.format(resource, args);

        // Prepend "[DE??] " to the message.
        return String.format("[%s] %s", name(), message);
    }
}
