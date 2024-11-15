import { TypeOf, z, ZodSchema } from "zod";

export const createUserSchema: ZodSchema = z
    .object({
        name: z.string({ required_error: "Name is required" }),
        email: z
            .string({ required_error: "Email is required" })
            .email("Please enter a valid email"),
        password: z
            .string({ required_error: "Password is required" })
            .min(8, "Password must be at least 8 characters"),
        passwordConfirmation: z
            .string({ required_error: "Password confirmation is required" })
            .min(8, "Password must be at least 8 characters"),
    })
    .refine((v) => v.password === v.passwordConfirmation, {
        message: "Passwords don't match",
        path: ["passwordConfirmation"],
    })
    .superRefine(({ password }, checkPassComplexity) => {
        const containsUppercase = (ch: string) => /[A-Z]/.test(ch);
        const containsLowercase = (ch: string) => /[a-z]/.test(ch);
        const containsSpecialChar = (ch: string) =>
            /[`!@#$%^&*()_\-+=\[\]{};':"\\|,.<>\/?~ ]/.test(ch);
        let countOfUpperCase = 0,
            countOfLowerCase = 0,
            countOfNumbers = 0,
            countOfSpecialChar = 0;
        for (let i = 0; i < password.length; i++) {
            let ch = password.charAt(i);
            if (!isNaN(+ch)) countOfNumbers++;
            else if (containsUppercase(ch)) countOfUpperCase++;
            else if (containsLowercase(ch)) countOfLowerCase++;
            else if (containsSpecialChar(ch)) countOfSpecialChar++;
        }
        if (
            countOfLowerCase < 1 ||
            countOfUpperCase < 1 ||
            countOfSpecialChar < 1 ||
            countOfNumbers < 1
        ) {
            checkPassComplexity.addIssue({
                code: "custom",
                message:
                    "Password does not meet complexity requirements. Password must contain" +
                    "at least one of uppercase, lowercase, numbers, and special characters",
            });
        }
    });

export type CreateUserInput = TypeOf<typeof createUserSchema>;
